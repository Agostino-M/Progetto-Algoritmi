//
//  merge_binary_insertion_sort.c
//  Progetto ASD - Esercizio 1
//
//  Created by Agostino Messina and Andrea Torella on 20/04/21.
//

#include <stdio.h>
#include <stdlib.h>
#include "merge_binary_insertion_sort.h"

static int binary_search(void **array, int (*comparator)(void *, void *), void *item, int low, int high);
static void binary_insertion_sort(void **array, int (*comparator)(void *, void *), int left, int right);
static void merge(void **array, int (*comparator)(void *, void *), int l, int m, int r);
static void merge_sort(void **array, int (*comparator)(void *, void *), int left, int right);

// A Binary Search based function to find the position where item should be inserted in a[low..high]
static int binary_search(void **array, int (*comparator)(void *, void *), void *item, int low, int high)
{
    if (high <= low)
        // return (item > a[low]) ?(low + 1) : low;
        return (((comparator))(item, array[low]) > 0 ? (low + 1) : low);

    int mid = (low + high) / 2;

    // if (item == a[mid])
    if (((comparator))(item, array[mid]) == 0)
        return mid + 1;

    // if (item > a[mid])
    if (((comparator))(item, array[mid]) > 0)
        return binary_search(array, comparator, item, mid + 1, high);

    return binary_search(array, comparator, item, low, mid - 1);
}

// Function that sort a sublist of sorted array of size 'K' using BinaryInsertion Sort
static void binary_insertion_sort(void **array, int (*comparator)(void *, void *), int left, int right)
{
    int i, loc, j;
    void *selected;

    for (i = left; i <= right; ++i)
    {
        j = i - 1;
        selected = array[i];

        // Find location where selected sould be inseretd
        loc = binary_search(array, comparator, selected, 0, j);

        // Move all elements after location to create space
        while (j >= loc)
        {
            array[j + 1] = array[j];
            j--;
        }
        array[j + 1] = selected;
    }
}

static void merge(void **array, int (*comparator)(void *, void *), int l, int m, int r)
{
    int n1 = m - l + 1;
    int n2 = r - m;

    // Create temp arrays
    void **L = (void **)malloc(sizeof(void *) * (unsigned long int)n1);
    void **R = (void **)malloc(sizeof(void *) * (unsigned long int)n2);

    // Copy data to temp arrays L[] and R[]
    for (int i = 0; i < n1; i++)
        L[i] = array[l + i];

    for (int j = 0; j < n2; j++)
        R[j] = array[m + 1 + j];

    // Merge the temp arrays back into arr[l..r]
    int i = 0; // Initial index of first subarray
    int j = 0; // Initial index of second subarray
    int k = l; // Initial index of merged subarray

    while (i < n1 && j < n2)
    {
        if (((comparator))(L[i], R[j]) <= 0)
        {
            array[k] = L[i];
            i++;
        }
        else
        {
            array[k] = R[j];
            j++;
        }
        k++;
    }

    // Copy the remaining elements of L[], if there are any
    while (i < n1)
    {
        array[k] = L[i];
        i++;
        k++;
    }

    // Copy the remaining elements of R[], if there are any
    while (j < n2)
    {
        array[k] = R[j];
        j++;
        k++;
    }

    free(L);
    free(R);
}

// l is for left index and r is right index of the sub-array of array to be sorted
static void merge_sort(void **array, int (*comparator)(void *, void *), int left, int right)
{
    if (left >= right)
        return;

    if (right - left + 1 <= K)
    {
        binary_insertion_sort(array, comparator, left, right);
        return;
    }

    int mid = left + (right - left) / 2;
    merge_sort(array, comparator, left, mid);
    merge_sort(array, comparator, mid + 1, right);
    merge(array, comparator, left, mid, right);
}

void **sorted_array_sort(void **array, int (*comparator)(void *, void *), int size)
{
    if (comparator == NULL)
    {
        fprintf(stderr, "sorted_array_sort: comparator parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    if (array == NULL)
    {
        fprintf(stderr, "sorted_array-sort: array parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }

    if (size <= 0)
    {
        fprintf(stderr, "sorted_array-sort: size parameter cannot be zero or negative");
        exit(EXIT_FAILURE);
    }

    merge_sort(array, comparator, 0, size - 1);
    return array;
}
