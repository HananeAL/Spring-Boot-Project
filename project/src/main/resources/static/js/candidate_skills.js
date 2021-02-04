// after page is fully loaded
window.onload = function () {
  document.getElementById('addButton').addEventListener('click', addSkill);
  document.getElementById('saveButton').addEventListener('click', sendSkills);
  document.getElementById(SKILLS_LIST_ID).addEventListener('click', removeSkill);
}

const ERROR_ID = "invalidSkill";
const SKILLS_LIST_ID = "skills";
const FORM_ID = "form";
const INPUT_FIELD_ID = "skill";
const LEVELS_LIST_ID = "level";
const REMOVE_BUTTON_CLASS = "removeButton";
const HOME = "/home";
const URL = "/signup/add-skills";

/* object that holds errors messages */
const errorMessage = {
  invalidSkillname: "invalid skill name",
  skillNameTooLong: "max length is 50",
  skillAlreadyExists: "skill already exists"
};

var skills = {}; // it will hols skills like this: {skill1:level1, skill2:level2...}

function sendSkills() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      window.location = HOME; // redirect the candidate to the home
    }
  }
  var skills = JSON.stringify(getSkills());
  xhttp.open("POST", URL, true);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.send(skills);
}

/* take skills from skills object and store it in an array and return it*/
function getSkills() {
  var data = [];
  for (var skillName in skills) {
    var skill = {name: skillName, level: skills[skillName]};
    data.push(skill);
  }
  return data;
}

function addSkill() {
  hideElement(ERROR_ID);
  var name = getSkillName().trim();
  if (name === '') {
  }
  else if (!isValid(name)) {
    displayError(errorMessage.invalidSkillname, ERROR_ID);
  }
  else if (!isLengthOK(name)) {
    displayError(errorMessage.skillNameTooLong, ERROR_ID);
  }
  else if (isSkillExists(skills, name)) {
    displayError(errorMessage.skillAlreadyExists, ERROR_ID);
  }
  else {
    var level = getSkillLevel();
    storeSkill(name, level); // add skill to the global object: skills
    displaySkill(name, level);
    cleanInput(INPUT_FIELD_ID);
  }
}

function removeSkill(e) {
  var removeButtonClicked = e.target.classList.contains(REMOVE_BUTTON_CLASS);
  if (removeButtonClicked) {
    var removeButton = e.target;
    var skill = removeButton.parentElement;
    var skillsList = document.getElementById(SKILLS_LIST_ID);
    skillsList.removeChild(skill);
    // remove fom the object skills
    var skillName = extractSkillName(skill.firstChild.textContent);
    delete skills[skillName];
  }
}

/* text= skillName (level) */
function extractSkillName(text) {
  var pattern = new RegExp("^[^(]+");
  return pattern.exec(text)[0].trim();
}

/* validate only a skill name */
function isValid(skillName) {
  var regex = new RegExp("^[a-z]([a-z- ])+$", "i");
  return regex.test(skillName);
}

/* check if skill name length is less than the limit (50 in this case) */
function isLengthOK(name) {
  var limit = 50;
  return name.trim().length <= limit;
}

/* check if a skill exist in the object skills */
function isSkillExists(skills, name) {
  return skills[name] != null;
}

/* put a skill object in the global array skills */
function storeSkill(name, level) {
  skills[name] = level;
}

function displaySkill(name, level) {
  var text = name + ' (' + level + ')';
  var skill = createElement('li', text);
  var removeButton = createElement('button', 'X'); // the delete button
  removeButton.className = REMOVE_BUTTON_CLASS;
  skill.appendChild(removeButton);
  var skills = document.getElementById(SKILLS_LIST_ID);
  skills.appendChild(skill);
}

function createElement(tagName, text) {
  var element = document.createElement(tagName);
  var textNode = document.createTextNode(text);
  element.appendChild(textNode);
  return element;
}

function displayError(message, id) {
  if (elementExists(id)) { // error already displayed
    showElement(id, message);
    return;
  }
  var error = createError(message, ERROR_ID);
  var form = document.getElementById(FORM_ID);
  form.appendChild(error);
}

function showElement(id, content) {
  var element = document.getElementById(id);
  if (element != null) {
    element.textContent = content;
    element.style.display = '';
  }
}

function hideElement(id) {
  var element = document.getElementById(id);
  if (element != null)
    element.style.display = 'none';
}

/* create an error message */
function createError(message, id) {
  var error = createElement('p', message);
  error.id = id;
  error.style.color = 'red';
  return error;
}

function elementExists(id) {
  return document.getElementById(id) != null;
}

function getSkillName() {
  return document.getElementById(INPUT_FIELD_ID).value;
}

function getSkillLevel() {
  return document.getElementById(LEVELS_LIST_ID).value;
}

function cleanInput(id) {
  var input = document.getElementById(id);
  if (input != null)
    input.value = '';
}