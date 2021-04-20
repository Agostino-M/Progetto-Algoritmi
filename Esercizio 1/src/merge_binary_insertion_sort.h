//
//  merge_binary_insertion_sort.h
//  Progetto ASD - Esercizio 1
//
//  Created by Agostino Messina and Andrea Torella on 20/04/21.
//

#ifndef MERGE_BINARY_INSERTION_SORT_H
#define MERGE_BINARY_INSERTION_SORT_H

// 'K' specifies the value of the sublists length for which Insertion Sort should be used instead of Merge Sort
#define K 10

// Array of generic to be sorted
typedef struct _SortedArray SortedArray;

// Function that sorts a SortedArray using a modified version of the Merge Sort in which
// sublists of length 'K' or less are sorted using the BinaryInsertion Sort and are then
// combined using the traditional merge sort mechanism
SortedArray *sort(void **array, int (*comparator)(void *, void *));

#endif /* MERGE_BINARY_INSERTION_SORT_H */
