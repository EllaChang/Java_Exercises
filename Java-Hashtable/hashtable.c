#include "hashtable.h"
#include <stdlib.h> // calloc, free

// Construct a new HashTable (completed).
HashTable_t* hash_new(void) {
	HashTable_t* self = malloc(sizeof(HashTable_t));
	assert(self != NULL);
	self->num_used_buckets = 0;
	self->num_elements = 0;
	// CHALLENGE: have new HashTable_t objects start with no memory until the first insert!
	// Allocate a small number of buckets to start!
	// Note: by using calloc, we avoid needing a for-loop to set entries to NULL!
	self->num_buckets = 8;
	self->buckets = calloc(self->num_buckets, sizeof(HashEntry_t*));
	return self;
}

// Allocate a new array of buckets and migrate elements to new list (if any).
static void hash_resize(HashTable_t* self) {
	// TODO: allocate a new set of buckets; copy data over!
}

// Return true if the hash table should grow:
static bool hash_should_resize(HashTable_t* self) {
	if (self->buckets == NULL) {
		return true;
	}
	double load = ((double) self->num_used_buckets) / ((double) self->num_buckets);
	return load > 0.75;
}

// Put a (key, value) pair into this list, overriding previous versions of the key if applicable.
void hash_put(HashTable_t* self, bytes_t* key, bytes_t* value) {
	assert(self != NULL);
	assert(key != NULL);
	assert(value != NULL);

	// Handle growing, at least:
	if (hash_should_resize(self)) {
		hash_resize(self);
	}
	
	// TODO: finish this method.
	int hash = bytes_hash(key);

	printf("called hash_put: ");
	bytes_print(stdout, key);
	printf(":%08x ", hash);
	bytes_print(stdout, value);
	printf("\n");
}

// Remove an entry matching this key in the association list.
void hash_remove(HashTable_t* self, bytes_t* key) {
	assert(self != NULL);
	assert(key != NULL);

	// TODO: finish this method.
	int hash = bytes_hash(key);
	printf("called hash_remove: ");
	bytes_print(stdout, key);
	printf(":%08x\n", hash);
}

// Lookup an entry matching this key and return a pointer to its value.
bytes_t* hash_get(HashTable_t* self, bytes_t* key) {
	assert(self != NULL);
	assert(key != NULL);

	int hash = bytes_hash(key);

	printf("called hash_get: ");
	bytes_print(stdout, key);
	printf(":%08x\n", hash);
	
	// TODO: finish this method; return NULL if missing!

	return NULL;
}

// Remove all entries from this list (try to free in the right places!)
void hash_clear(HashTable_t* self) {
	assert(self != NULL);

	for (size_t i=0; i<self->num_buckets; i++) {
		
		// Traverse each bucket as a linked list; freeing stuff.
		HashEntry_t* current = self->buckets[i];
		HashEntry_t* next = NULL;
		while(current != NULL) {
			// grab next:
			next = current->next;
			// delete current:
			bytes_free(current->key);
			bytes_free(current->value);
			free(current);
			// move to next
			current = next;
		}
		
		// Start of list no longer points anywhere:
		self->buckets[i] = NULL;
	}

	// Finish clearing data:
	self->num_used_buckets = 0;
	self->num_elements = 0;
}

// Lookup the number of elements in this data structure; prefer O(1) to O(n).
size_t hash_size(HashTable_t* self) {
	assert(self != NULL);
	return self->num_elements;
}
