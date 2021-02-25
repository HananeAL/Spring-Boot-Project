window.onload = function() {
  let offers = document.querySelectorAll('.offer');
  offers.forEach(offer => {
    offer.addEventListener('click', getOffer);
  });
  // global variabes
  window.descContainer = document.getElementById('description');
  window.skillsContainer = document.getElementById('skills');
  window.responsibilitiesContainer = document.getElementById('responsibilities');
};

function getOffer(e) {
  let xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      let offer = JSON.parse(this.response);
      // console.log(offer);
      displayOffer(offer);
    }
  }
  // console.log(`offer id: ${this.id}`);
  const URL = `/company/offers/${this.id}`; // this.id <=> offer id
  xhttp.open("GET", URL, true);
  xhttp.send();
}

function displayOffer(offer) {
  displayHeader(offer);
  displayBody(offer);
}

function displayHeader(offer) {
  put('position', offer.position);
  put('company-name', offer.company.name);
  put('city', offer.city);
  put('creation-date', offer.creationDate);
  put('type', offer.type);
}

function displayBody(offer) {
  displayDescription(offer.description);
  displaySkills(offer.skills);
  displayResponsibilities(offer.responsibilities);
}

function displayDescription(description) {
  descContainer.innerHTML = '';
  let title = createElement('h2', 'Description');
  descContainer.appendChild(title);
  let desc = createElement('p', description);
  descContainer.appendChild(desc);
}

// skills: an array of skill objects
function displaySkills(skills) {
  skillsContainer.innerHTML = '';
  let title = createElement('h2', 'Compétences');
  skillsContainer.appendChild(title);
  skills.forEach(skill => addSkill(skill));
}

function addSkill(skill) {
  let item = createElement('li', `${skill.name}: ${skill.level}`);
  skillsContainer.appendChild(item);
}

function displayResponsibilities(responsibilities) {
  responsibilitiesContainer.innerHTML = '';
  let title = createElement('h2', 'Responsabilitées');
  responsibilitiesContainer.appendChild(title);
  responsibilities.forEach(respo => addResponsibility(respo));
}


function addResponsibility(responsibility) {
  let item = createElement('li', `${responsibility.name}`);
  responsibilitiesContainer.appendChild(item);
}

// create a html element
function createElement(tagName, text) {
  let element = document.createElement(tagName);
  element.appendChild(document.createTextNode(text));
  return element;
}

/* affect the value (as a innerHTML) to the element identified by id */
function put(id, value) {
  let element = document.getElementById(id);
  if (element != null) {
    element.innerHTML = value;
  }
}