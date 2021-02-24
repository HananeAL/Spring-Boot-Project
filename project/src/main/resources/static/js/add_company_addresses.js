window.onload = function () {
  document.querySelector('#add-button button').addEventListener('click', addAddress);
  document.getElementById(ADDRESS_LIST_ID).addEventListener('click', removeAddress);
  document.querySelector('#save-button button').addEventListener('click', sendAddresses);
}

const FORM_ID = "form";
const STREET_FIELD_ID = "street";
const CITIES_LIST_ID = "cities";
const ADDRESS_LIST_ID = "addresses";
const ERROR_ID = "error";
const REMOVE_BUTTON_CLASS = "remove-button";
const PROFILE = "/company/offers";
const URL = "/signup/add-company-addresses";

const errorMessage = {
  addressExists: "a similar address exists in the list"
}

var addresses = {}

function sendAddresses() {
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      window.location = PROFILE; // redirect the company to it's profile page
    }
  }
  var addresses = JSON.stringify(getAddresses());
  xhttp.open("POST", URL, true);
  xhttp.setRequestHeader("Content-type", "application/json");
  xhttp.send(addresses);
}

/* return the addresses as an array of object */
function getAddresses() {
  var data = [];
  for (var key in addresses) {
    data.push(addresses[key]);
  }
  return data;
}

function addAddress() {
  var street = getStreet();
  var city = getCity();
  hideElement(ERROR_ID);
  if (isAddressExists(street, city)) {
    displayError(errorMessage.addressExists, ERROR_ID, FORM_ID);
  }
  else {
    displayAddress(street, city);
    storeAddress(street, city);
  }
}

function displayAddress(street, city) {
  var text = constructText(street, city);
  var address = createElement('li', text);
  var removeButton = createRemoveButton();
  address.appendChild(removeButton);
  addElement(address, ADDRESS_LIST_ID);
}

function createRemoveButton() {
  var removeButton = createElement('button', 'X');
  removeButton.className = REMOVE_BUTTON_CLASS;
  return removeButton;
}

function storeAddress(street, city) {
  var key = street + city;
  addresses[key] = { street: street, city: city };
}

function removeAddress(e) {
  var removeButtonClicked = e.target.classList.contains(REMOVE_BUTTON_CLASS);
  if (removeButtonClicked) {
    var removeButton = e.target;
    var address = removeButton.parentElement;
    removeElement(address, ADDRESS_LIST_ID);
    // remove from the object address
    var key = getKey(address.firstChild.textContent);
    delete addresses[key];
  }
}

/* return the key of an address based on the the text version of this address
  the text version of an address follow this pattern: "street (city)" 
*/
function getKey(text) {
  text = text.replace('(', '');
  text = text.replace(')', '');
  text = text.replace(/[ ]+/, '');
  return text;
}

function getStreet() {
  return getInputContent(STREET_FIELD_ID);
}

/* get the content of an input field */
function getInputContent(inputId) {
  var input = document.getElementById(inputId);
  if (input != null)
    return input.value;
}

function getCity() {
  return getSelectedItem(CITIES_LIST_ID);
}

/* get the selected item in a select element */
function getSelectedItem(listId) {
  var list = document.getElementById(listId);
  if (list != null)
    return list.value;
}

/* 
  check if an address with the same street and city already exists or if an address without street
  but has the same city.
  simply we can't have like this: 
  case 1:   street1 city1 - street1 city1
*/
function isAddressExists(street, city) {
  return addresses[street + city] != null;
}

/* construct text to be displayed based on street and city values */
function constructText(street, city) {
  street = street.trim();
  if (street === '')
    return city;
  else
    return street + ' (' + city + ')';
}

function createElement(tagName, text) {
  var element = document.createElement(tagName);
  var textNode = document.createTextNode(text);
  element.appendChild(textNode);
  return element;
}

/* create an error message */
function createError(message, id) {
  var error = createElement('p', message);
  error.id = id;
  error.style.color = 'red';
  return error;
}

/* append the element to the parent identified by "parentId" */
function addElement(element, parentId) {
  var parent = document.getElementById(parentId);
  if (parent == null) return;
  parent.appendChild(element);
}

function displayError(message, id, parentId) {
  if (elementExists(id)) { // error already displayed
    showElement(id, message);
  }
  else {
    var error = createError(message, id);
    addElement(error, parentId);
  }
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

/* remove a child element of the parent that has id equals to parentId */
function removeElement(element, parentId) {
  var parent = document.getElementById(parentId);
  if (parent != null) {
    parent.removeChild(element);
  }
}