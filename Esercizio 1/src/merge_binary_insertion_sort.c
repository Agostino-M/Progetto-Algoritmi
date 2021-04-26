//
//  merge_binary_insertion_sort.c
//  Progetto ASD - Esercizio 1
//
//  Created by Agostino Messina and Andrea Torella on 20/04/21.
//

#include <stdio.h>
#include <stdlib.h>
#include "merge_binary_insertion_sort.h"

static void initialize_array(void *array, int (*comparator)(void *, void *));
static int binary_search(void *item, int low, int high);
static void binary_insertion_sort(int left, int right);
static void merge(int l, int m, int r);
static void merge_sort(int left, int right);

SortedArray *sorted_array;

// Function that initialize the sorted array structure
static void initialize_array(void *array, int (*comparator)(void *, void *))
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
}

// A Binary Search based function to find the position where item should be inserted in a[low..high]
static int binary_search(void *item, int low, int high)
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
        return binary_search(item, mid + 1, high);
    
    return binary_search(item, low, mid - 1);
}

// Function that sort a sublist of sorted array of size 'K' using BinaryInsertion Sort
static void binary_insertion_sort(int left, int right)
{
    int i, loc, j;
    void *selected;
    
    for (i = left; i <= right; ++i)
    {
        j = i - 1;
        selected = sorted_array->array[i];
        
        // Find location where selected sould be inseretd
        loc = binary_search(selected, 0, j);
        
        // Move all elements after location to create space
        while (j >= loc)
        {
            sorted_array->array[j + 1] = sorted_array->array[j];
            j--;
        }
        sorted_array->array[j + 1] = selected;
    }
}



static void merge(int l, int m, int r)
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
}

// l is for left index and r is right index of the sub-array of array to be sorted
static void merge_sort(int left, int right)
{
    if(left>=right)
            return;//returns recursively
        
    if (right - left + 1 <= K)
    {
        binary_insertion_sort(left, right);
        return;
    }
    
    int mid = left + (right - left) / 2;
    merge_sort(left, mid);
    merge_sort(mid + 1, right);
    merge(left, mid, right);
}

void *sorted_array_sort(void *array, int (*comparator)(void *, void *), int left, int right)
{
    initialize_array(array, comparator);
    merge_sort(left, right);
    return sorted_array;
}

void sorted_array_free_memory()
{
    if (sorted_array == NULL)
    {
        fprintf(stderr, "sorted_array_free_memory: sorted_array parameter cannot be NULL");
        exit(EXIT_FAILURE);
    }
    free(sorted_array->array);
    free(sorted_array);
}