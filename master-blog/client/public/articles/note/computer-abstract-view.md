# Computer Abstract View

```mermaid
flowchart TB
A[User] --> |Interact| B[Applications];
B --> |Respond| A;
B --> |Request Resources| C[Operating System];
C --> |Dole out Resources| B;
C --> |Input| D[Hardware];
D --> |Output| C;
```
