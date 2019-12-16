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
- 🏁 Connection Manager
    - open/close/test connection
    - manage active connection
- 🏁 Metadata Loader
    - node
    - menu
    - form
- 🏁 Instruction Parser
- 🏃 Query
    - sql executor
    - redis script executor
    - mongodb script executor
- More...

> 🏁 Finished all in list<br/>
> ✔ Finished one <br/>
> 🏃 Doing


## Directory structure

```
dms
 ├─ dm              // which dm you want to develop, add to this dir by `git clone`
 |   ├─ dm-base     // an abstract implementation of dms-sdk
 |   ├─ dm-plugin   // help you to build dm
 |   └─ dm-mysql    // an implementation of dms-sdk
 ├─ dms-core        // dynamic call dm impl when request comming
 ├─ dms-sdk         // dm development base on dms-sdk
 └─ tools           // common tools
```

> dm-base is common implementation of dms-sdk


## Start

### Pull project

```
# pull dms
git clone https://github.com/monkeydp/dms.git

# pull submodule tools
cd dms
git submodule update --init

# pull dm-base and dm-plugin
cd dm
git clone  https://github.com/monkeydp/dm-base
git clone  https://github.com/monkeydp/dm-plugin

# pull dm which you want to develop, like dm-mysql
git clone  https://github.com/monkeydp/dm-mysql
```

### Import module

Open dms with IDEA, `Project Structure → Modules → "+" → Import Module`, import **dms-core**, **dm-mysql**

### Config DmsApplication

`Edit Configurations → Spring Boot → DmsApplication`, set `Working directory` = `%MODULE_WORKING_DIR%`

### Run DmsApplication

Click `Run` Button

 
## Swagger Api

http://127.0.0.1:8080/doc.html


## H2-Database

http://127.0.0.1:8080/h2-console


## Abbreviation

abbreviation | original
:--- | :---
**# A** |
abstr | abstract
annot | annotation
&nbsp;|
**# C** |
comp | component
conn | connection
ctx | context
cp | connection profile
cpw | connection profile wrapper
cw | connection wrapper
&nbsp;|
**# D** |
db | database
desc | description
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
md | menu definition
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