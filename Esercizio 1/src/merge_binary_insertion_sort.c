//
//  merge_binary_insertion_sort.c
//  Progetto ASD - Esercizio 1
//
//  Created by Agostino Messina and Andrea Torella on 20/04/21.
//

#include <stdio.h>
#include <stdlib.h>
#include "merge_binary_insertion_sort.h"

static void initialize_array(void **array, int (*comparator)(void *, void *));
static int array_length(void **array);
static int binary_search(void *item, SortedArray *sorted_array, int low, int high);
static void binary_insertion_sort(SortedArray *sorted_array);

struct _SortedArray
{
    void **array;
    int (*comparator)(void *, void *);
    unsigned long length;
};

// Function that initialize the sorted array structure
static void initialize_array(void **array, int (*comparator)(void *, void *))
{
    if (array == NULL)
    {
        fprintf(stderr, "initialize_array: array cannot be NULL.");
        exit(EXIT_FAILURE);
    }

    SortedArray *sorted_array = (SortedArray *)malloc(sizeof(SortedArray));
    if (sorted_array == NULL)
    {
        fprintf(stderr, "initialize_array: unable to allocate memory for the sorted array");
        exit(EXIT_FAILURE);
    }

    sorted_array->array = array;
    sorted_array->length = array_length(array);
    sorted_array->comparator = comparator;
}

static int array_length(void **array)
{
    return sizeof(array) / sizeof(array[0]);
}

// A Binary Search based function to find the position where item should be inserted in a[low..high]
static int binary_search(void *item, SortedArray *sorted_array, int low, int high)
{
    if (high <= low)
        // return (item > a[low]) ?(low + 1) : low;
        return (((*sorted_array->comparator))(item, (sorted_array->array)[low]) > 0) ? (low + 1) : low;

    int mid = (low + high) / 2;

    // if (item == a[mid])
    if (((*sorted_array->comparator))(item, (sorted_array->array)[mid]) == 0)
        return mid + 1;

    // if (item > a[mid])
    if (((*sorted_array->comparator))(item, (sorted_array->array)[mid]) > 0)
        return binarySearch(sorted_array, item, mid + 1, high);

    return binarySearch(sorted_array, item, low, mid - 1);
}

// Function that sort a sublist of sorted array of size 'K' using BinaryInsertion Sort
static void binary_insertion_sort(SortedArray *sorted_array)
{

    int i, loc, j;
    void *selected;
    for (i = 1; i < K; ++i)
    {
        j = i - 1;
        selected = sorted_array->array[i];

        // Find location where selected sould be inseretd
        loc = binarySearch(sorted_array, selected, 0, j);

        // Move all elements after location to create space
        while (j >= loc)
        {
            sorted_array->array[j + 1] = sorted_array->array[j];
            j--;
        }
        sorted_array->array[j + 1] = selected;
    }
}

SortedArray *sort(void **array, int (*comparator)(void *, void *))
{
    // TO-DO ...
    return NULL;
}
