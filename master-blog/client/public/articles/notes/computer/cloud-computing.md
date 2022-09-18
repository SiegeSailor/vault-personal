- Public cloud – available via Internet to anyone willing to pay
- Private cloud – run by a company for the company’s own use
- Hybrid cloud – includes both public and private cloud components
- Software as a Service (SaaS) – one or more applications available via the Internet (i.e., word processor)
- Platform as a Service (PaaS) – software stack ready for application use via the Internet (i.e., a database server)
- Infrastructure as a Service (IaaS) – servers or storage available over Internet (i.e., storage available for backup use)

# Environment

```mermaid
flowchart TB
Internet --> Firewall
Internet -.->|Customer Requests| CCI
Firewall --> LB[Load Balancer]
Firewall --> CCI[Cloud Customer Interface]
CCI -->|Cloud Management Commands| CMS

subgraph Cloud
    VM1[Virtual Machine/Server]
    VM2[Virtual Machine/Server]
    Storage
    CMS[Cloud Management Service]
end

LB --> Cloud
```
