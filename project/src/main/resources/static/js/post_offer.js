window.onload = function () {
  document.getElementById('post-offer').addEventListener('click', sendOffer);
  document.getElementById('add-skill').addEventListener('click', addSkill);
  document.getElementById('add-responsibility').addEventListener('click', addResponsibility);
}

const NOT_EMPTY = 1;
const TEXT_ONLY = 2;
const NUMBER = 3;
const POSITIVE_NUMBER = 4;
const OPTIONAL = 5;
const REQUIRED = 6;
const DATE = 7;
const PRESENT_OR_FUTURE = 8;
const FUTURE = 9;

var offer = {
  skills: [],
  responsibilities: []
};

var fields = { // value can be an object: {id, constraints}
  speciality: {
    name: "specialité",
    id: "speciality",
    constraints: [REQUIRED, TEXT_ONLY]
  },
  position: {
    id: "position",
    constraints: [REQUIRED, TEXT_ONLY]
  },
  startDate: {
    name: "date de début",
    id: "start-date",
    constraints: [REQUIRED, PRESENT_OR_FUTURE]
  },
  closingDate: {
    name: "date de fermuture",
    id: "closing-date",
    constraints: [OPTIONAL, PRESENT_OR_FUTURE]
  },
  description: {
    id: "description",
    constraints: [REQUIRED]
  }
};

function sendOffer() {
  var isValid = collecte();
  if (!isValid) return;

  const URL = "/company/post-offer";
  var data = JSON.stringify(offer);
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      window.location = "/company/offers"; // redirect to thes url if offer is saved
    }
  }
  xhttp.open("POST", URL, true);
  xhttp.setRequestHeader('Content-type', 'application/json');
  xhttp.send(data);
}

function collecte() {
  var isValid = collecteFirstPart();
  if (isValid) {
    getSkills();
    getResponsibilities();
    getAdditionalInfo();
    return true;
  }
  else {
    return false;
  }
}

function collecteFirstPart() {
  offer.type = getOfferType();
  offer.city = getOfferCity();
  var isValid = true;
  for (var field in fields) {
    var id = fields[field].id; // the id of the element we want to validate it's value
    var value = getInputValue(id);
    // res = {valid:true|false, error:string}
    var res = validate(value, fields[field].constraints);
    if (!res.valid) {
      isValid = false;
      var errorMssg = generateErrorMess(field, fields[field], res.error);
      displayErrorAfter(errorMssg, id + '-error', id);
      delete offer[field];
    }
    else {
      hideElement(id + '-error');
      offer[field] = value;
    }
  }
  return isValid;
}

function getOfferType() {
  var select = document.getElementById('type');
  if (select != null) {
    return select.value;
  }
}

function getOfferCity() {
  var select = document.getElementById('city');
  if (select != null) {
    return select.value;
  }
}

function getSkills() {
  var skillWrappers = document.querySelectorAll('.skill-wrapper');
  for (var i = 0; i < skillWrappers.length; i++) {
    var input = skillWrappers[i].querySelector('input');
    var skillName = input.value;
    if (!isEmpty(skillName)) {
      var select = skillWrappers[i].querySelector('select');
      var skillLevel = select.value;
      var skill = { name: skillName, level: skillLevel };
      if (!isSkillExists(skill, offer.skills))
        offer.skills.push(skill);
    }
  }
}

function getResponsibilities() {
  var responsibilityWrappers = document.querySelectorAll('.responsibility-wrapper');
  for (var i = 0; i < responsibilityWrappers.length; i++) {
    var textarea = responsibilityWrappers[i].querySelector('textarea');
    var responsibility = { name: textarea.value };
    if (!isEmpty(responsibility.name) && !isResponsibilityExists(responsibility, offer.responsibilities)) {
      offer.responsibilities.push(responsibility);
    }
  }
}

function getAdditionalInfo() {
  offer.isPublic = document.getElementById('public').checked;
  offer.receiveRecommendations = document.getElementById('receive-recomm').checked;
}

/* check if skill already exists in skills(array of object) */
function isSkillExists(skill, skills) {
  for (var s of skills) {
    if (s.name.toLowerCase() === skill.name.toLowerCase()) return true;
  }
  return false;
}

/* check if responsibility exists in responsibilities(array of object) */
function isResponsibilityExists(responsibility, responsibilities) {
  for (var r of responsibilities) {
    if (r.name.toLowerCase() === responsibility.name.toLowerCase()) return true;
  }
  return false;
}


/* 
  attributes = {name:*****, id:*****, constraints:[***]} 
  genericMess = "***** {{ field }} *****"
*/
function generateErrorMess(defaultName, attributes, genericMess) {
  var fieldName = attributes.name;
  if (fieldName == null) fieldName = defaultName;
  return genericMess.replace('{{ field }}', fieldName);
}

function getInputValue(id) {
  var input = document.getElementById(id);
  if (input != null)
    return input.value;
}

/* check if the value respect the constraints(array) */
function validate(value, constraints) {
  var result = { valid: true, error: undefined };

  if (constraints.includes(OPTIONAL) && isEmpty(value)) {
    // nothing to do
  }
  else if (constraints.includes(REQUIRED) && isEmpty(value)) {
    result.valid = false;
    result.error = errors.required;
  }
  else if (constraints.includes(TEXT_ONLY) && !isText(value)) {
    result.valid = false;
    result.error = errors.textOnly;
  }
  else if (constraints.includes(POSITIVE_NUMBER) && !isPositiveNumber(value)) {
    result.valid = false;
    result.error = errors.positiveNumber;
  }
  else if (constraints.includes(PRESENT_OR_FUTURE) && !isPresentOrFuture(value)) {
    result.valid = false;
    result.error = errors.presentOrFuture;
  }
  else if (constraints.includes(FUTURE) && !isFuture(value)) {
    result.valid = false;
    result.error = errors.future;
  }
  return result;
}

var errors = {
  required: "{{ field }} est obligatoire",
  textOnly: "{{ field }} ne peut contenir que du texte",
  positiveNumber: "{{ field }} n'est un nombre positive",
  presentOrFuture: "{{ field }} doit etre en present ou future",
  future: "{{ field }} doit etre en future"
};

function isEmpty(value) {
  return value == null || value.trim() === '';
}

function isText(value) {
  var pattern = /^[a-z ]+$/;
  return pattern.test(value);
}

function isPositiveNumber(value) {
  var pattern = /^[0-9]+$/;
  return pattern.test(value);
}

/* check if the given date is in the present or in the future, we assume date is a string */
function isPresentOrFuture(date) {
  var now = new Date();
  date = new Date(Date.parse(date));
  if (date.getFullYear() > now.getFullYear()) return true;
  if (date.getFullYear() < now.getFullYear()) return false;

  if (date.getMonth() > now.getMonth()) return true;
  if (date.getMonth() < now.getMonth()) return false;

  if (date.getDate() >= now.getDate()) return true;
  if (date.getDate() < now.getDate()) return false;
}

function isFuture(date) {
  var now = new Date();
  date = new Date(Date.parse(date));
  if (date.getFullYear() > now.getFullYear()) return true;
  if (date.getFullYear() < now.getFullYear()) return false;

  if (date.getMonth() > now.getMonth()) return true;
  if (date.getMonth() < now.getMonth()) return false;

  if (date.getDate() > now.getDate()) return true;
  if (date.getDate() <= now.getDate()) return false;
}

function insertAfter(element, ref) {
  if (ref == null) return;
  var parent = ref.parentElement;
  if (parent == null) return;
  var next = ref.nextElementSibling;
  var isRefTheLastChild = next == null;
  if (isRefTheLastChild) { // ref is the last element child
    parent.appendChild(element);
  }
  else {
    parent.insertBefore(element, next);
  }
}

function displayErrorAfter(message, errorId, refId) {
  if (elementExists(errorId)) { // error already displayed
    showElement(errorId, message);
  }
  else {
    var error = createError(message, errorId);
    error.className = 'error';
    var ref = document.getElementById(refId);
    insertAfter(error, ref);
  }
}

/* create an error message */
function createError(message, id) {
  var error = createElement('p', message);
  error.id = id;
  error.style.color = 'red';
  return error;
}

function createElement(tagName, text) {
  var element = document.createElement(tagName);
  if (text != undefined) {
    var textNode = document.createTextNode(text);
    element.appendChild(textNode);
  }
  return element;
}

/* append the element to the parent identified by "parentId" */
function addElement(element, parentId) {
  var parent = document.getElementById(parentId);
  if (parent == null) return;
  parent.appendChild(element);
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

function elementExists(id) {
  return document.getElementById(id) != null;
}





// this part is responsible for add-skill and add-responsibility

const SKILL_NAME_LABEL = "Compétences: ";
const SKILL_LEVEL_LABEL = 'Niveau de compétences: ';
const BEGINNER = "beginner";
const INTERMIDATE = "intermidate";
const ADVANCED = "advanced";
const SKILL_INPUT_CLASS = "skill";
const SELECT_SKILL_CLASS = "skill-level";
const SKILLS_CONTAINER = "skills-container";
const RESPO_CONTAINER = "responsibilities-container";


function addSkill() {

  var skillWrapper = createElement('div');
  var attributes = {
    class: "skill-wrapper "
  };
  setAttribute(skillWrapper, attributes);

  var skillNameLabel = createElement('label', SKILL_NAME_LABEL);

  var input = createElement('input');
  var attributes = {
    type: "text",
    placeholder: "Compétence",
    class: "skill form-control mt-0"
  };
  
  setAttribute(input, attributes);

  var skillLevelLabel = createElement('label', SKILL_LEVEL_LABEL);

  var optionsValues = [BEGINNER, INTERMIDATE, ADVANCED];
  var select = createSelectElement(optionsValues);
  var attributes = {
    class: "skill-level form-control" 
  };
  
  setAttribute(select, attributes);

  var elements = [skillNameLabel, input, skillLevelLabel, select];
  addElements(skillWrapper, elements);

  var skillsContainer = document.getElementById(SKILLS_CONTAINER);
  addElements(skillsContainer, [skillWrapper]);
}


function addResponsibility() {
  var respoWrapper = createElement('div');
  var attributes = {
    placeholder: "Résponsabilité",
    class: "responsibility-wrapper"
  };
  setAttribute(respoWrapper, attributes);

  var label = createElement('label', 'Responsibility');

  var textarea = createElement('textarea');
  textarea.value = '';
  var attributes = {
    class: "responsibility form-control"/**/
  };
  setAttribute(textarea, attributes);

  var elements = [label, textarea];
  addElements(respoWrapper, elements);

  var container = document.getElementById(RESPO_CONTAINER);
  addElements(container, [respoWrapper]);
}


/* we assume attrubutes is an object: {..., attribute : value, ...} */
function setAttribute(element, attributes) {
  if (element == null) return;
  for (var attribute in attributes) {
    var value = attributes[attribute];
    element.setAttribute(attribute, value);
  }
}

/* we assume elements is an array */
function addElements(parent, elements) {
  if (parent == null) return;
  for (var element of elements) {
    parent.appendChild(element);
  }
}

function createElement(tagName, text) {
  var element = document.createElement(tagName);
  if (text != undefined) {
    var textNode = document.createTextNode(text);
    element.appendChild(textNode);
  }
  return element;
}

/* 
  create a select element with the given id, and contains option elements 
  we assume optionsValues is an array
*/
function createSelectElement(optionsValues) {
  var select = createElement('select');
  for (var optionValue of optionsValues) {
    var option = createOption(optionValue, optionValue);
    select.appendChild(option);
  }
  return select;
}

/* create option element */
function createOption(value, text) {
  var option = createElement('option', text);
  option.value = value;
  return option;
}












