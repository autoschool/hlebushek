<div align="center">
    <img src='https://pp.vk.me/c623216/v623216453/16f39/ixNbpHR_jPw.jpg'>
</div>
=========
### Run application ###
clone project repository to local:
```
$git clone https://github.com/autoschool/hlebushek.git
```
go to project directory:
```
$cd hlebushek
```
run server:
```
$mvn jetty:run
```
open in browser:
```
open http://localhost:8080
```

=========
### Create branch ###
clone project repository to local:
```
$git clone https://github.com/autoschool/hlebushek.git
```
go to project directory
```
$cd hlebushek
```
create & change to new branch:
```
$git checkout -b [branch_name]
```

=========
### Run system tests
  *  mvn clean verify
  *  mvn test
  
  See report in hlebushek\target\site\index.html