# config-demo
Simple demo of [Lightbend Config](https://github.com/lightbend/config) usage.

## Build

`sbt assembly` will build the a fat jar of the application with all dependencies.

## Run

### Runtime configuration

#### Global structure

 - [reference.conf][1] contains working defaults that you might want to override. 
 - [application-fixed.conf][2] contains definitions that settle some naming convention. You can override some values that are used in some ones.
 - [application-runtime.conf][3] is not part of the build and contains value you must provide because they are mandatory but not provided elsewhere. This is typically a file you want to provide at deploy or launch time depending on target environment.
 - `HOSTNAME`, `APP_NAME` and `INSTANCE_ID` environment variable can override some values. See below. 

#### Instance id

You should provide `org.jro.configdemo.instance-id` at launch time by one of this means:
1. Add `-Dorg.jro.configdemo.instance-id=<instance-id-value>` on JVM command line. This will directly override `org.jro.configdemo.instance-id`.
2. Add `INSTANCE_ID` env variable in your launch config. This will override the value because of the `instance-id` definition in [application-fixed.conf](./src/main/resources/application-fixed.conf).

If you do not choose one of these, the instance value will be the value of `HOSTNAME` env variable. 
If it is not defined either, the instance id won't be defined and the application launch will abort with an error.

#### Bootstrap servers

You should provide a config file containing servers hosts and ports.
A sample one is provided but not included in built jar: [application-runtime.conf][3]

This is the environment-specific part that can be injected by adding a Lightbend Config specific JVM option:
 - `-Dconfig.file=<path to conf file>` where the path is pointing to the config file inside a volume-mounted folder (Docker, K8s...
 - `-Dconfig.resource=<path to conf file>` where the path is relative to the JVM classpath
 - `-Dconfig.url=<conf url>` where the url is pointing to a resource on an http server centralizing configuration.

Here again, you can override config with direct JVM options (`-Dpath.of.variable`) but because there is an array and a bunch of values, this is the typical case where a file is more convenient. 

Not providing the configuration variables provided in the sample config will abort application launch.

#### Launch

`TODO`

## TODO

1. Change docker image form JDK/JRE due to RedHat stopping openjdk repository support.
2. Launch doc

[1]: ./src/main/resources/reference.conf
[2]: ./src/main/resources/application-fixed.conf
[3]: ./src/main/resources/application-runtime.conf
