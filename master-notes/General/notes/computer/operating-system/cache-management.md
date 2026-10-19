if Cache smaller than storage being cached

- Cache management important design problem
- Cache size and replacement policy

- Multitasking environments must be careful to use most recent value, no matter where it is stored in the memory hierarchy
- Multiprocessor environment must provide **cache coherency** in hardware such that all CPUs have the most recent value in their cache
- Distributed environment situation even more complex
  - Several copies of a datum can exist

The following are the requirements for cache coherence:

## Write Propagation

Changes to the data in any cache must be propagated to other copies (of that cache line) in the peer caches.

## Transaction Serialization

Reads/Writes to a single memory location must be seen by all processors in the same order.

Theoretically, coherence can be performed at the load/store granularity. However, in practice it is generally performed at the granularity of cache blocks.
