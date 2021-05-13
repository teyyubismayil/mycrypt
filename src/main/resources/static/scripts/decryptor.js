async function decrypt(text, d, n) {
    const encodedBlocks = text.split('.');
    const decodedBlocks = [];
    encodedBlocks.forEach(block => {
        decodedBlocks.push(modExp(block, d, n));
    });
    const decrypted = translateBlocksToText(decodedBlocks)
    let padding = 0
    for (let i = decrypted.length - 1; i >= 0; i--) {
        if (decrypted.charAt(i) === '-') {
            padding++;
        } else {
            break
        }
    }
    return decrypted.substring(0, decrypted.length - padding)
}

function translateBlocksToText(blocks) {
    const text = []
    blocks.forEach(block => {
        const numberBlocks = numberToBlocks(block)
        numberBlocks.forEach(b => {
            text.push(reverseAlphabet.get(b))
        })
    })
    return text.join('')
}

function numberToBlocks(n) {
    const blocks = []
    while (n > 0) {
        blocks.push(n % 100)
        n = ~~(n / 100)
    }
    return blocks.reverse()
}
