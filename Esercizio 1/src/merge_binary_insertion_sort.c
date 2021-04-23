//
//  merge_binary_insertion_sort.c
//  Progetto ASD - Esercizio 1
//
//  Created by Agostino Messina and Andrea Torella on 20/04/21.
//

#include <stdio.h>
#include <stdlib.h>
#include "merge_binary_insertion_sort.h"

static SortedArray *initialize_array(void **array, int (*comparator)(void *, void *));
static int binary_search(void *item, SortedArray *sorted_array, int low, int high);
static void binary_insertion_sort(SortedArray *sorted_array);
static void **merge(SortedArray *sorted_array, int l, int m, int r);
static void **merge_sort(SortedArray *sorted_array, int left, int right);

SortedArray *sorted_array = NULL;

// Function that initialize the sorted array structure
static SortedArray *initialize_array(void **array, int (*comparator)(void *, void *))
{
    if (array == NULL)
    {
        fprintf(stderr, "initialize_array: array cannot be NULL.");
        exit(EXIT_FAILURE);
    }

    sorted_array = (SortedArray *)malloc(sizeof(SortedArray));

    if (sorted_array == NULL)
    {
        fprintf(stderr, "initialize_array: unable to allocate memory for the sorted array");
        exit(EXIT_FAILURE);
    }

    sorted_array->array = array;
    sorted_array->comparator = comparator;

    return sorted_array;
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
        return binary_search(sorted_array, item, mid + 1, high);

    return binary_search(sorted_array, item, low, mid - 1);
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
        loc = binary_search(sorted_array, selected, 0, j);

        // Move all elements after location to create space
        while (j >= loc)
        {
            sorted_array->array[j + 1] = sorted_array->array[j];
            j--;
        }
        sorted_array->array[j + 1] = selected;
    }
}

static void **merge(SortedArray *sorted_array, int l, int m, int r)
{
    int n1 = m - l + 1;
    int n2 = r - m;

    // Create temp arrays
    void **L = (void **)malloc(sizeof(void *) * n1);
    void **R = (void **)malloc(sizeof(void *) * n2);

    // Copy data to temp arrays L[] and R[]

    for (int i = 0; i < n1; i++)
        L[i] = sorted_array->array[l + i];

    for (int j = 0; j < n2; j++)
        R[j] = sorted_array->array[m + 1 + j];

    // Merge the temp arrays back into arr[l..r]
    int i = 0; // Initial index of first subarray
    int j = 0; // Initial index of second subarray
    int k = l; // Initial index of merged subarray

    while (i < n1 && j < n2)
    {
        if (((*sorted_array->comparator))(L[i], R[j]) <= 0)
        {
            sorted_array->array[k] = L[i];
            i++;
        }
        else
        {
            sorted_array->array[k] = R[j];
            j++;
        }
        k++;
    }

    // Copy the remaining elements of L[], if there are any
    while (i < n1)
    {
        sorted_array->array[k] = L[i];
        i++;
        k++;
    }

    // Copy the remaining elements of R[], if there are any
    while (j < n2)
    {
        sorted_array->array[k] = R[j];
        j++;
        k++;
    }

    free(L);
    free(R);
    return sorted_array->array;
}

// l is for left index and r is right index of the sub-array of array to be sorted
static void **merge_sort(SortedArray *sorted_array, int left, int right)
{
    if (right - left + 1 == K)
    {
        binary_insertion_sort(sorted_array);
        return sorted_array->array;
    }

    int m = (right - left + 1) / 2;
    merge_sort(sorted_array, left, right);
    merge_sort(sorted_array, left + 1, right);
    return merge(sorted_array, left, m, right);
}

void **sorted_array_sort(void **array, int (*comparator)(void *, void *), int left, int right)
{
    sorted_array = initialize_array(array, comparator);
    return merge_sort(sorted_array, left, right);
}
