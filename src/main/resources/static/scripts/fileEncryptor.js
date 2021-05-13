const url = '/api/encryption';
let file = null

window.onload = (event) => {
    const fileWindow = document.getElementById('fileWindow');
    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(event => {
        fileWindow.addEventListener(event, preventDefaults, false);
    })
    fileWindow.addEventListener('drop', onDrop, false);
    const fileInput = document.getElementById('fileInput');
    fileInput.addEventListener('change', onFileInput, false);
    const passwordInput = document.getElementById('passwordInput');
    passwordInput.addEventListener('change', onPasswordChange, false);
    const passwordConfirm = document.getElementById('passwordConfirm');
    passwordConfirm.addEventListener('change', onPasswordConfirmChange, false);
    const encryptBtn = document.getElementById('encryptBtn');
    encryptBtn.addEventListener('click', onEncryptClick, false);
}

function preventDefaults (e) {
    e.preventDefault();
    e.stopPropagation();
}

function onFileInput(e) {
    file = e.target.files[0];
    handleFileInput();
}

function onDrop(e) {
    file = e.dataTransfer.files[0];
    handleFileInput();
}

function handleFileInput() {
    document.getElementById('fileWindow').classList.add('display-none');
    document.getElementById('passwordWindow').style.display = 'block';
}

function onPasswordChange(e) {
    const fillErr1 = document.getElementById('fillErr1');
    const value = document.getElementById('passwordInput').value;
    if (value === undefined || value === null || value === '') {
        fillErr1.style.display = 'block'
    } else {
        fillErr1.style.display = 'none'
    }
}

function onPasswordConfirmChange(e) {
    const fillErr2 = document.getElementById('fillErr2');
    const sameErr = document.getElementById('sameErr');
    const passwordInput = document.getElementById('passwordInput');
    const value = document.getElementById('passwordConfirm').value;
    if (value === undefined || value === null || value === '') {
        fillErr2.style.display = 'block';
    } else {
        fillErr2.style.display = 'none';
    }
    if (passwordInput.value !== value) {
        sameErr.style.display = 'block';
    } else {
        sameErr.style.display = 'none';
    }
}

function onEncryptClick(e) {
    onPasswordConfirmChange();
    onPasswordChange();
    const passwordInputVal = document.getElementById('passwordInput').value;
    const passwordConfirmVal = document.getElementById('passwordConfirm').value;
    if (passwordInputVal === passwordConfirmVal && passwordInputVal !== undefined
        && passwordInputVal !== null && passwordInputVal !== '') {
        encryptAndDownload(file, passwordInputVal);
    }
}

function encryptAndDownload(f, password) {
    let content;
    let id;
    readFileAsBaseString(f)
        .then(baseString => {
                console.log(baseString)
            const prefixEnd = baseString.indexOf(',');
            const prefix = baseString.substring(0, prefixEnd + 1);
            const extension = prefix.substring(5, prefix.length - 8);
            content = baseString.substring(prefixEnd + 1, baseString.length);
            setProgress(20)
            return getKey(password, extension, f.name)
        })
        .then(res => {
            id = res.id
            setProgress(40)
            return encrypt(content, res.encryptionKey.e, res.encryptionKey.n)
        })
        .then(encrypted => {
            setProgress(100)
            download(id + '.' + encrypted, generateFileName(f.name))
            onDownloaded()
        })
}

async function readFileAsBaseString(file) {
    return await new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => resolve(fileReader.result);
        fileReader.readAsDataURL(file);
    });
}

async function getKey(password, extension, fileName) {
    const body = {
        password: password,
        extension: extension,
        fileName: fileName,
    }
    const headers = {
        'Content-Type': 'application/json'
    }
    return await new Promise(resolve => {
        fetch(url, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(body)
        })
            .then(res => res.json())
            .then(json => resolve(json))
    });
}

function generateFileName(fileName) {
    const index = fileName.lastIndexOf('.');
    if (index === -1) {
        return fileName + ".myscript";
    }
    return fileName.substring(0, index) + ".myscript";
}

function download(text, filename) {
    const downloadLink = document.createElement('a');
    downloadLink.href = 'data:text/plain;charset=utf-8,' + encodeURIComponent(text);
    downloadLink.download = filename;
    downloadLink.click();
    // downloadLink.remove();
}

function onDownloaded() {
    setProgress(0)
    document.getElementById('passwordInput').value = '';
    document.getElementById('passwordConfirm').value = '';
    document.getElementById('passwordWindow').style.display = 'none';
    document.getElementById('fileWindow').classList.remove('display-none');
}

function setProgress(n) {
    const bar = document.getElementById('bar');
    bar.style.width = n + '%'
}
