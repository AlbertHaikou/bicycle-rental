function CustomValidation(input) {
    this.invalidities = [];
    this.validityChecks = [];

    //add reference to the input node
    this.inputNode = input;

    //trigger method to attach the listener
    this.registerListener();
}

CustomValidation.prototype = {
    addInvalidity: function (message) {
        this.invalidities.push(message);
    },
    getInvalidities: function () {
        return this.invalidities.join('. \n');
    },
    checkValidity: function (input) {
        for (var i = 0; i < this.validityChecks.length; i++) {

            var isInvalid = this.validityChecks[i].isInvalid(input);
            if (isInvalid) {
                this.addInvalidity(this.validityChecks[i].invalidityMessage);
            }

            var requirementElement = this.validityChecks[i].element;

            if (requirementElement) {
                if (isInvalid) {
                    requirementElement.classList.add('invalid');
                    requirementElement.classList.remove('valid');
                } else {
                    requirementElement.classList.remove('invalid');
                    requirementElement.classList.add('valid');
                }

            }
        }
    },
    checkInput: function () {

        this.inputNode.CustomValidation.invalidities = [];
        this.checkValidity(this.inputNode);

        if (this.inputNode.CustomValidation.invalidities.length === 0 && this.inputNode.value !== '') {
            this.inputNode.setCustomValidity('');
        } else {
            var message = this.inputNode.CustomValidation.getInvalidities();
            this.inputNode.setCustomValidity(message);
        }
    },
    registerListener: function () {

        var CustomValidation = this;

        this.inputNode.addEventListener('keyup', function () {
            CustomValidation.checkInput();
        });


    }

};


/* ----------------------------
	Validity Checks
---------------------------- */

var usernameValidityChecks = [
    {
        isInvalid: function (input) {

            return !input.value.match(/[@]/g);
        },
        invalidityMessage: 'Need\'s to contain @',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(1)')
    },
    {
        isInvalid: function (input) {
            return !input.value.match(/.+@.+[\.]/);
        },
        invalidityMessage: 'Needs to contain . after @ and 1 or more symbols',
        element: document.querySelector('label[for="username"] .input-requirements li:nth-child(2)')
    }
];

var passwordValidityChecks = [
    {
        isInvalid: function (input) {
            return input.value.length < 6;
        },
        invalidityMessage: 'This input must at least 6 characters long',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(1)')
    },
    {
        isInvalid: function (input) {
            return !input.value.match(/[0-9]/g);
        },
        invalidityMessage: 'At least 1 number is required',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(2)')
    },
    {
        isInvalid: function (input) {
            return !input.value.match(/[a-z]/g);
        },
        invalidityMessage: 'At least 1 lowercase letter is required',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(3)')
    },
    {
        isInvalid: function (input) {
            return !input.value.match(/[A-Z]/g);
        },
        invalidityMessage: 'At least 1 uppercase letter is required',
        element: document.querySelector('label[for="password"] .input-requirements li:nth-child(4)')
    }
];

var passwordRepeatValidityChecks = [
    {
        isInvalid: function () {
            return passwordRepeatInput.value != passwordInput.value;
        },
        invalidityMessage: 'This password needs to match the first one'
    }
];

/* ----------------------------
	Setup the CustomValidation prototype for each input
---------------------------- */

var usernameInput = document.getElementById('username');
var passwordInput = document.getElementById('password');
var passwordRepeatInput = document.getElementById('password_repeat');


usernameInput.CustomValidation = new CustomValidation(usernameInput);
usernameInput.CustomValidation.validityChecks = usernameValidityChecks;

passwordInput.CustomValidation = new CustomValidation(passwordInput);
passwordInput.CustomValidation.validityChecks = passwordValidityChecks;

passwordRepeatInput.CustomValidation = new CustomValidation(passwordRepeatInput);
passwordRepeatInput.CustomValidation.validityChecks = passwordRepeatValidityChecks;


/* ----------------------------
	Event Listeners
---------------------------- */

var inputs = document.querySelectorAll('input:not([type="submit"])');


var submit = document.querySelector('input[type="submit"');
var form = document.getElementById('loginForm');

function validate() {
    for (var i = 0; i < inputs.length; i++) {
        inputs[i].CustomValidation.checkInput();
    }
}

submit.addEventListener('click', validate);
form.addEventListener('submit', validate);