# Assignment 1 — Design and Analysis of Algorithms

This repository contains the implementation of **divide-and-conquer algorithms** for the course *Design and Analysis of Algorithms*.

## Goals
- Implement classic D&C algorithms with safe recursion.
- Analyze recurrences with Master Theorem and Akra–Bazzi intuition.
- Collect metrics (time, recursion depth, comparisons, allocations).
- Report results with clean Git workflow.

## Implemented Algorithms
- MergeSort (linear merge, cutoff, reusable buffer)
- QuickSort (randomized pivot, smaller-first recursion)
- Deterministic Select (Median-of-Medians)
- Closest Pair of Points (2D, O(n log n))

## Testing
- JUnit 5 unit tests
- Benchmarks planned with JMH

## CI/CD
GitHub Actions runs Maven build & tests on each push to `main`.
