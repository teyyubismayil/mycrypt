const authenticateUrl = '/authenticate';
const registerUrl = '/register';

window.onload = (event) => {
    const usernameIn = document.getElementById('usernameIn');
    usernameIn.addEventListener('change', onUsernameInChange, false);
    const passwordIn = document.getElementById('passwordIn');
    passwordIn.addEventListener('change', onPasswordInChange, false);
    const signInBtn = document.getElementById('signInBtn');
    signInBtn.addEventListener('click', onSignInBtnClick, false);
    const createBtn = document.getElementById('createBtn');
    createBtn.addEventListener('click', onCreateBtnClick, false);

    const emailUp = document.getElementById('emailUp');
    emailUp.addEventListener('change', onEmailUpChange, false);
    const usernameUp = document.getElementById('usernameUp');
    usernameUp.addEventListener('change', onUsernameUpChange, false);
    const passwordUp = document.getElementById('passwordUp');
    passwordUp.addEventListener('change', onPasswordUpChange, false);
    const passwordConUp = document.getElementById('passwordConUp');
    passwordConUp.addEventListener('change', onPasswordConUpChange, false);
    const signUnBtn = document.getElementById('signUnBtn');
    signUnBtn.addEventListener('click', onSignUpBtnClick, false);
}

function onUsernameInChange(e) {

}

function onPasswordInChange(e) {

}

function onSignInBtnClick(e) {
    const username = document.getElementById('usernameIn').value;
    const password = document.getElementById('passwordIn').value;
    fetch(`${authenticateUrl}?username=${username}&password=${password}`, {
        method: 'POST'
    })
        .then(res => {
            window.open('/', '_self')
        })
}

function onCreateBtnClick(e) {
    switchToSignUp();
}


function onEmailUpChange(e) {

}

function onUsernameUpChange(e) {

}

function onPasswordUpChange(e) {

}

function onPasswordConUpChange(e) {

}

function onSignUpBtnClick(e) {
    const email = document.getElementById('emailUp').value;
    const username = document.getElementById('usernameUp').value;
    const password = document.getElementById('passwordUp').value;
    const body = {
        username: username,
        password: password,
        email: email
    }
    const headers = {
        'Content-Type': 'application/json'
    }
    fetch(registerUrl, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(body)
    })
        .then(res => {
            window.open('/')
        })
}


function switchToSignUp() {
    document.getElementById('signInWindow').style.display = 'none';
    document.getElementById('signUpWindow').style.display = 'block';
}

function switchToSignIn() {
    document.getElementById('signUpWindow').style.display = 'none';
    document.getElementById('signInWindow').style.display = 'block';
}
