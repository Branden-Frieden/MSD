#include <iostream>

using namespace std;
byte* shuffle(byte table[]){
    for(int i = 255; i > 1; i--){
        int j = rand() % i;
        byte temp = table[i];
        table[i] = table[j];
        table[j] = temp;
    }
    return table;
}

std::string encrypt(std::string state, ::uint8_t key[], byte* subTables[]){
    for(int round = 0; round < 16; round++){
        // xor with key
        for(int i = 0; i < state.length(); i++){
            state[i] ^= key[i];
        }

        // substitute from table
        for(int i = 0; i < state.length(); i++){
            state[i] = (char) subTables[round % 8][state[i]];
        }

        // rotate
        uint8_t firstBit = state[0] & 0x80;
        for (int i = 0; i < state.size() - 1; i++) {
            state[i] = (state[i] << 1) | ((state[i+1] & 0x80) >> 7);
        }
        state[7] = (state[7] << 1) | (firstBit >> 7);

    }
    return state;
}

std::string decrypt(std::string state, ::uint8_t key[], byte* subTables[]){

    for(int round = 0; round < 16; round++) {

        // Reverse the rotation step
        uint8_t lastBit = state[7] & 0x01;
        for (int i = state.size()-1; i > 0; i--) {
            state[i] = (state[i] >> 1) | ((state[i-1] & 0x01) << 7);
        }
        state[0] = (state[0] >> 1) | (lastBit << 7);

        // Reverse the substitution step
        for (int i = 0; i < state.length(); i++) {
            int index;
            for(int j = 0; j < 256; j++){
                if(subTables[round % 8][j] == (byte) state[i]){
                    index = j;
                    break;
                }
            }
            state[i] = (char) subTables[round % 8][index];
        }

        for(int i = 0; i < state.length(); i++){
            state[i] = state[i] ^ key[i];
        }
    }
    return state;
}

::uint8_t* makeKey(std::string password){
    ::uint8_t key[] = {0, 0, 0, 0, 0, 0, 0, 0};
    for(int i = 0; i < password.length(); i++){
        key[ i % 8 ] = key[i % 8] ^ password[ i ];
    }
    return key;
}


int main() {

    std::string password = "password123";

    ::uint8_t *key = makeKey(password);

    byte* subTables[8];
    byte table[256];
    for(int i = 0; i < 256; i++){
        table[i] = (byte) i;
    }
    subTables[0] = table;

    for(int i = 1; i < 8; i++){
        byte tempTable[256];
        for(int j = 0; j < 256; j++){
            tempTable[j] = subTables[i-1][j];
        }
        subTables[i] = shuffle(tempTable);
    }

    std::string input = "12345678";

    std::string encrypted = encrypt(input, key, subTables);

    std::cout << encrypted << std::endl;

    std::string decrypted = decrypt(encrypted, key, subTables);

    std::cout << decrypted;

    return 0;
}
