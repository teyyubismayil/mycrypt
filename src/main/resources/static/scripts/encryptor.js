async function encrypt(text, e, n) {
    const blocks = translateTextToBlocks(text, n.toString().length);
    const encodedBlocks = [];
    blocks.forEach(block => {
        encodedBlocks.push(modExp(block, e, n));
    });
    return encodedBlocks.join(".");
}

function translateTextToBlocks(text, l) {
    const blockSize = l / 2
    const paddingSize = text.length % blockSize;
    for (let i = 0; i < paddingSize; i++) {
        text += '-'
    }
    const blocks = [];
    for (let i = 0; i < text.length; i += blockSize) {
        let block = ''
        for(let j = 0; j < blockSize; j++) {
            const code = alphabet.get(text.charAt(i + j))
            if (code < 10) {
                block += ('0' + code)
            } else {
                block += code
            }
        }
        blocks.push(parseInt(block));
    }
    return blocks
}
