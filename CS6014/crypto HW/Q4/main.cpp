#include <iostream>

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

uint8_t* initialize(uint8_t *key){
    uint8_t * s = new uint8_t [256];
    for(int i = 0; i < 256; i++){
        s[i] = i;
    }

    int j = 0;
    for(int i = 0; i < 256; i++){
        j = (j + s[i] + key[i % 8]) % 256;
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
    return s;
}

uint8_t* createCipher(uint8_t s[], std::string input) {
    int i = 0;
    int j = 0;
    uint8_t *k = new uint8_t[input.length()];

    for(int count = 0; count < 256; count++) {
        i = (i + 1) % 256;
        j = (j + s[i]) % 256;
        int temp = s[i];
        s[i] = s[j];
        s[j] = temp;
        k[count] = s[(s[i] + s[j]) % 256];
    }
    return k;
}

std::string encrypt(std::string input, std::string password){
    ::uint8_t *key = makeKey(password);
    uint8_t* s = initialize(key);
    uint8_t* k = createCipher(s, input);

    std::string output = "";
    for(int i = 0; i < input.length(); i++){
        output += k[i] ^ input[i];
    }
    return output;
}


std::string decrypt(std::string input, std::string password){
    ::uint8_t *key = makeKey(password);
    uint8_t* s = initialize(key);
    uint8_t* k = createCipher(s, input);

    std::string output = "";
    for(int i = 0; i < input.length(); i++){
        output += k[i] ^ input[i];
    }
    return output;
}


int main() {

    std::string password = "otherPassword!23";
    std::string input = "message to encrypt";

    std::string encrypted = encrypt(input, password);
    std::string decrypted = decrypt(encrypted, password);
    std::string wrongDecrypted = decrypt(encrypted, "wrongPassword");
    std::string encryptedWithSameKey = encrypt("other message to encrypt", password);

    std::cout << "encrypted: "<< encrypted << std::endl;
    std::cout << "decrypted: " << decrypted << std::endl;
    std::cout << "wrong key: " << wrongDecrypted << std::endl;

    std::string encryptedsXordTogether = "";
    for(int i = 0; i < encrypted.length(); i++){
        encryptedsXordTogether += encrypted[i] ^ encryptedWithSameKey[i];
    }

    std::cout << "same keyed inputs xOr together: " << encryptedsXordTogether << std::endl;


    std::string salaryencrypted = encrypt("salary: $1000", password);
    salaryencrypted[9] ^= 8;
    salaryencrypted[10] ^= 9;
    salaryencrypted[11] ^= 9;
    salaryencrypted[12] ^= 9;
    std::string salarydecrypted = decrypt(salaryencrypted, password);

    std::cout << salarydecrypted << std::endl;
    return 0;
}