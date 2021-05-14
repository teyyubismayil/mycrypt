const url = '/api/decryption';
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
    const decryptBtn = document.getElementById('decryptBtn');
    decryptBtn.addEventListener('click', onDecryptClick, false);
    const againBtn = document.getElementById('againBtn');
    againBtn.addEventListener('click', onAgainClick, false);
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
    if (file.name.endsWith('.myscript') || file.name.endsWith('.txt')) {
        document.getElementById('fileWindow').classList.add('display-none');
        document.getElementById('decryptWindow').style.display = 'block';
    }
}

function onPasswordChange(e) {
    const fillErr1 = document.getElementById('fillErr1');
    const value = e.target.value;
    if (value === undefined || value === null || value === '') {
        fillErr1.style.display = 'block'
    } else {
        fillErr1.style.display = 'none'
    }
}

function onDecryptClick(e) {
    const passwordInputVal = document.getElementById('passwordInput').value;
    if (passwordInputVal !== undefined && passwordInputVal !== null && passwordInputVal !== '') {
        decryptAndDownload(file, passwordInputVal);
    }
}

async function decryptAndDownload(f, password) {
    let id;
    let encrypted;
    let fileName;
    let extension;
    readFileAsText(f)
        .then(text => {
                console.log(text)
            id = text.substring(0, text.indexOf('.'));
            encrypted = text.substring(text.indexOf('.') + 1, text.length)
            setProgress(20)
            return getKey(id, password)
        })
        .then(res => {
                console.log(res);
            fileName = res.fileName;
            extension = res.extension;
            setProgress(40)
            decrypt(encrypted, res.decryptionKey.d, res.decryptionKey.n)
                .then(decrypted => {
                    setProgress(100)
                    download(decrypted, extension, fileName);
                    onDownloaded()
                })
        })
        .catch(reason => {
            openErrorWindow(reason);
        })
}

async function readFileAsText(file) {
    return await new Promise((resolve) => {
        let fileReader = new FileReader();
        fileReader.onload = (e) => resolve(fileReader.result);
        fileReader.readAsText(file);
    });
}

async function getKey(id, password) {
    const body = {
        id: id,
        password: password
    }
    const headers = {
        'Content-Type': 'application/json'
    }
    return await new Promise((resolve, reject) => {
        fetch(url, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(body)
        })
            .then(res => {
                if(res.status === 400) {
                    reject('Incorrect password')
                } else if (res.status === 404) {
                    reject('File is not found')
                }
                return res.json()
            })
            .then(json => resolve(json))
    });
}

function download(text, extension,  filename) {
    console.log(extension)
    const downloadLink = document.createElement('a');
    downloadLink.href = 'data:' + extension + ';base64,' + text;
    downloadLink.download = filename;
    downloadLink.click();
}

function onDownloaded() {
    setProgress(0)
    document.getElementById('passwordInput').value = '';
    document.getElementById('decryptWindow').style.display = 'none';
    document.getElementById('fileWindow').classList.remove('display-none');
}

function setProgress(n) {
    const bar = document.getElementById('bar');
    bar.style.width = n + '%'
}

function openErrorWindow(reason) {
    setProgress(0);
    document.getElementById('decryptWindow').style.display = 'none';
    document.getElementById('errorWindow').style.display = 'block';
    document.getElementById('errorMsg').innerText = reason;
}

function onAgainClick(e) {
    location.reload();
}
