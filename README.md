# github-analyzer


#### REST API which using Spring 5's Reactive WebClient for querying GitHub API. 
+ find repositories with name querying
+ view repository details
    * commits list
    * contirbutors list
+ bookmark results


Prerequisites: 
* Docker (can be download https://www.docker.com/products/docker-desktop) 

Run instructions:
* clone app
* generate github API token from your accoutn and copy it to application.properties file
   + github.api.token=token yourtoken
* open terminal(cmd)
* docker build --tag analyzer:1.0 .
* docker run --publish 8080:8080 --detach --name bb analyzer:1.0

API: 

* GET api/v1/repositories/${repo_name}/contributors?author=${owner}
* GET api/v1/repositories/${repo_name}/commits?author=${owner}
* GET api/v1/repositories/${repo_name}/timeline?author=${owner}
* GET api/v1/repositories/search?keyword=${key}&page=${page_no}

* PUT api/v1/bookmarks/${userUUID}
* GET api/v1/bookmarks?userUUId=${userUUID}


