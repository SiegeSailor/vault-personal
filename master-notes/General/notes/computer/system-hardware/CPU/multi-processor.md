- Asymmetric Multiprocessing – each processor is assigned a specie task.
- Symmetric Multiprocessing – each processor performs all tasks

# Dual Core Design

- Multi-chip and multicore
- Systems containing all chips
  - Chassis containing multiple separate systems

```mermaid
flowchart TB
   subgraph Processor
      direction TB
      subgraph C1[CPU Core 1]
         direction TB
         Registers1[Registers] --> LC1[L1 Cache]
      end

      subgraph C2[CPU Core 2]
         direction TB
         Registers2[Registers] --> LC2[L1 Cache]
      end

      L2[L2 Cache]
      C1 --> L2
      C2 --> L2
   end

   L2 --> MM[Main Memory]
```

## Non-Uniform Memory Access System

https://hackmd.io/@hPMCWajOS-ORQdEEAQ04-w/Hkd1rsonP
