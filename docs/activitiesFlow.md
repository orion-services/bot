---
layout: default
title: Activities flow
nav_order: 4
---

# Activities flow

This is a proposal of configuration file for the flow of activities. 

```yaml
workflows:
 -
    name: "Circle of Writers"
    steps:
      - 
        action: circle
        parameters:
          turns: 10 
  - 
    name: "Pair review"
    steps: 
      - 
        action: divide
        parameters: 
          divideBy: 2
      - 
        action: circle
        parameters: 
          turns: 2
      - 
        action: join
      - 
        action: circle
```