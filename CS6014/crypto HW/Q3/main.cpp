#include <iostream>

using namespace std;
byte* shuffle(byte table[]){
    for(int i = 255; i > 0; i--){
        int j = rand() % i;
        byte temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }
    return table;
}

void makeSubstitutionTables(byte *subTables[8]) {
    byte *table = new byte[256];
    for(int i = 0; i < 256; i++){
        table[i] = (byte) i;
    }

    subTables[0] = table;
    byte *tempTable = new byte[256];
    for(int i = 0; i < 256; i++){
        tempTable[i] = (byte) i;
    }

    for(int i = 1; i < 8; i++){
        tempTable = shuffle(tempTable);
        byte* temp = new byte[256];
        for(int j = 0; j < 256; j++){
            temp[j] = tempTable[j];
        }
        subTables[i] = temp;
        temp = nullptr;
        delete[] temp;
    }
    table = nullptr;
    delete[] table;
}

void makeDecryptTables(byte *subTables[8], byte *decryptTables[8]) {

    for(int i = 0; i < 8; i++){
        byte* temp = new byte[256];
        for(int j = 0; j < 256; j++){
            temp[(uint8_t) subTables[i][j]] = (byte)(j);
        }
        decryptTables[i] = temp;
    }
}

std::string encrypt(std::string state, ::uint8_t key[], byte* subTables[]){
    for(int round = 0; round < 16; round++){
        // xor with key
        state[round % 8] ^= key[round % 8];

        // substitute from table
        for(int i = 0; i < 8; i++){
            state[i] = (char) subTables[i][(uint8_t) state[i]];
        }

        // rotate
        uint8_t firstBit = state[0] & 0x80;
        for (int i = 0; i < 7; i++) {
            state[i] = (state[i] << 1) | ((state[i + 1] & 0x80) >> 7);
        }
        state[7] = (state[7] << 1) | (firstBit >> 7);

    }

    return state;
}


std::string decrypt(std::string state, ::uint8_t key[], byte** subTables){

    for(int round = 15; round >= 0; round--) {

        // Reverse the rotation step
        uint8_t lastBit = state[7] & 0x01;
        for (int i = 7; i > 0; i--) {
            state[i] = ((state[i] >> 1) & 0x7F) | (((state[i - 1] & 0x01) << 7) & 0x80);
        }
        state[0] = ((state[0] >> 1) & 0x7F) | (lastBit << 7 & 0x80);

        // Reverse the substitution step
        for(int i = 0; i < 8; i++){
            state[i] = (char) subTables[i%8][(uint8_t) state[i]];
        }

        state[round % 8] ^= key[round % 8];

    }

    return state;
}

::uint8_t* makeKey(std::string password){
    ::uint8_t* key = new ::uint8_t[8];
    for(int i = 0; i < 8; i++){
        key[i] = 0;
    }
    for(int i = 0; i < password.length(); i++){
        key[ i % 8 ] = key[i % 8] ^ password[ i ];
    }
    return key;
}


int main() {

    std::string input = "yeehaw69";
    std::string password = "otherPassword!23";

    // setup key and tables
    ::uint8_t *key = makeKey(password);

    byte* subTables[8];
    makeSubstitutionTables(subTables);

    byte* decryptTables[8];
    makeDecryptTables(subTables, decryptTables);



    // encrypt and decrypt
    std::string encrypted = encrypt(input, key, subTables);
    std::string decrypted = decrypt(encrypted, key, decryptTables);
    ::uint8_t *wrongPasswordKey = makeKey("wrongPassword");
    std::string wrongPassDecrpyted = decrypt(encrypted, wrongPasswordKey, decryptTables);
    std::string alteredEncrypted = encrypted;
    alteredEncrypted[4] += 1;
    std::string alteredDecrpyted = decrypt(alteredEncrypted, key, decryptTables);


    // print to console
    std::cout << "encrypted data: " << encrypted << std::endl;
    std::cout << "decrypted data: " << decrypted << std::endl;
    std::cout << "wrong Password: " << wrongPassDecrpyted << std::endl;
    std::cout << "altered decrypt: " << alteredDecrpyted << std::endl;

    return 0;
}




