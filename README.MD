# DMS

- DMS: Datasource Management Service
- DM: Datasource Module


## Dependency

- Kotlin 1.3.5
- Springboot 2.1.9
- Gradle 5.6.2


## Description

This project is a datasource management service of **Cloud Database** based on sdk development

Following datasource would be developed as an example:
- mysql (5.7, 8.0)
- sqlserver
- redis
- mongodb


## Project Progress

- Base Function (OK)
    - auto package dm zip to dms when dms start up
    - dynamic load libs and drivers from diff dm
- Connection Manager (OK)
    - open/close/test connection
    - manager active connection
- Metadata Loader (Doing...)
    - navigate
    - menu
    - form
- Command Executor
    - sql
    - redis script
    - mongodb script
- More...


## Directory structure

```
dms
 ├─ dm              // which dm you want to develop, add to this dir by `git clone`
 |   └─ dm-mysql    // git clone https://github.com/monkeydp/dm-mysql
 ├─ dms-core        // dynamic call dm impl when request comming
 ├─ dms-sdk         // dm development base on dms-sdk
 └─ tools           // git submodule update --init 
```

 
## Api

access http://127.0.0.1:8080/doc.html


## Database

access http://127.0.0.1:8080/h2-console


## Abbreviation

abbreviation | original
:--- | :---
**# A** |
abstr | Abstract
&nbsp;|
**# C** |
conn | Connection
cp | Connection Profile
cpw | Connection Profile Wrapper
cw | Connection Wrapper
&nbsp;|
**# D** |
db | Database
ds | Datasource
def | Definition
&nbsp;|
**# E** |
err | error
&nbsp;|
**# G** |
gnd | Group Node Definition
&nbsp;|
**# I** |
Instr | instruction
&nbsp;|
**# M** |
msg | message
&nbsp;|
**# N** |
nd | Node Definition