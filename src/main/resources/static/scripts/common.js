document.addEventListener('DOMContentLoaded', () => {
    const username = document.getElementById('username');
    username.addEventListener('click', onUsernameClick, false);
    if (isLogged()) {
        document.getElementById('comSignInBtn').style.display = 'none'
        document.getElementById('usernameDiv').style.display = 'block'
    } else {
        document.getElementById('comSignInBtn').style.display = 'block';
        document.getElementById('usernameDiv').style.display = 'none';
    }
});

function isLogged() {
    return getUsername() != null;
}

function getUsername() {
    const username = document.getElementById('username').innerText;
    if(username !== null && username !== undefined && username !== '') {
        return username;
    }
    return null;
}

function onUsernameClick(e) {
    console.log('username click');
    document.getElementById("myDropdown").classList.toggle('show');
}
//implement logout