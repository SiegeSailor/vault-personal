---
title: ER Diagram Mapping
description: A document sent with the resume that provides additional information about skills and experiences related to the job.
slug: er-diagram-mapping
authors: maintainer
tags: [interview]
---

BRANCH

```
| BID* | Phone | Address | Name |
```

SPECIALTY

```
| BID*_ | Specialty* |
```

WORK_HISTORY

```
| SID*_ | Start Date* | End Date | BID |
```

STAFF

```
| SID* | Name | Address | Classification | Salary |
```

CONTRACT

```
| CONID* | Date | Price | SID_ | CID_ | VIN_ |
```

CLIENT

```
| CID | Name | Phone | Address | BID_ |
```

CAR

```
| VIN* | Type | Make | Model | Year | Color | BID_ |
```
