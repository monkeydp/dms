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

- 🏁 Base Function
    - auto package dm zip to dms when dms start up
    - dynamic load libs and drivers from diff dm
-  🏁 Connection Manager
    - open/close/test connection
    - manager active connection
- 🏃 Metadata Loader
    - node  ✔ <br/> (navigator)
    - menu  ✔
    - form
- Command Executor
    - sql
    - redis script
    - mongodb script
- More...

> 🏁 Finished all in list<br/>
> ✔ Finished one <br/>
> 🏃 Doing


## Directory structure

```
dms
 ├─ dm              // which dm you want to develop, add to this dir by `git clone`
 |   ├─ dm-base     // git clone https://github.com/monkeydp/dm-base
 |   └─ dm-mysql    // git clone https://github.com/monkeydp/dm-mysql
 ├─ dms-core        // dynamic call dm impl when request comming
 ├─ dms-sdk         // dm development base on dms-sdk
 └─ tools           // git submodule update --init 
```

> dm-base is common implementation of dms-sdk
 
## Api

access http://127.0.0.1:8080/doc.html


## Database

access http://127.0.0.1:8080/h2-console


## Abbreviation

abbreviation | original
:--- | :---
**# A** |
abstr | abstract
annot | annotation
&nbsp;|
**# C** |
conn | connection
ctx | context
cp | connection profile
cpw | connection profile wrapper
cw | connection wrapper
&nbsp;|
**# D** |
db | database
ds | datasource
def | definition
&nbsp;|
**# E** |
err | error
&nbsp;|
**# G** |
&nbsp;|
**# I** |
instr | instruction
&nbsp;|
**# M** |
mi | menu item
mid | menu item definition
msg | message
&nbsp;|
**# N** |
nd | node definition
&nbsp;|
**# P** |
prop | property
&nbsp;|
**# S** |
std | standard
struct | structure
&nbsp;|
**# T** |
tpl | template