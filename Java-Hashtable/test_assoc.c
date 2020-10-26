#include "hashtable.h"

int main(void) {
	HashTable_t* dictionary = hash_new();
    
    assert(hash_size(dictionary) == 0);
    
    hash_put(dictionary, bytes_copy_str("A"), bytes_copy_str("a"));
    assert(hash_get(dictionary, bytes_copy_str("a")) == NULL);
    assert(hash_get(dictionary, bytes_copy_str("B")) == NULL);
    assert(bytes_eqc(hash_get(dictionary, bytes_copy_str("A")), "a"));
    assert(1 == hash_size(dictionary));

    hash_put(dictionary, bytes_copy_str("B"), bytes_copy_str("b"));
    assert(hash_get(dictionary, bytes_copy_str("a")) == NULL);
    assert(hash_get(dictionary, bytes_copy_str("b")) == NULL);
    assert(bytes_eqc(hash_get(dictionary, bytes_copy_str("A")), "a"));
    assert(bytes_eqc(hash_get(dictionary, bytes_copy_str("B")), "b"));
    assert(2 == hash_size(dictionary));

    hash_clear(dictionary);
    assert(hash_size(dictionary) == 0);
    
    hash_put(dictionary, bytes_copy_str("A"), bytes_copy_str("a"));
    hash_put(dictionary, bytes_copy_str("B"), bytes_copy_str("b"));
    hash_remove(dictionary, bytes_copy_str("B"));
    
    assert(hash_get(dictionary, bytes_copy_str("a")) == NULL);
    assert(hash_get(dictionary, bytes_copy_str("B")) == NULL);
    assert(bytes_eqc(hash_get(dictionary, bytes_copy_str("A")), "a"));
    assert(1 == hash_size(dictionary));
    
    hash_remove(dictionary, bytes_copy_str("A"));
    assert(0 == hash_size(dictionary));

    return 0;
}