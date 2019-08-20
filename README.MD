### settings
- your sendgrind api key in application.properties
- change filename of application_override.properties

### start docker services
```

```

### start superset
```

```

### deploy with serveo
```
ssh -R infit.serveo.net:80:localhost:3000 serveo.net
ssh -R recito.serveo.net:80:localhost:8080 serveo.net
```

### Enhancements
- set application.properties for differents environments (así Good Gurdian no detecta el error de api key!)
- 