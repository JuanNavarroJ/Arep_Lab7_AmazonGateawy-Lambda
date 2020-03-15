# Patrones Arquitecturales

## Link de prueba

http://ec2-54-152-103-77.compute-1.amazonaws.com:4567/inputdata

## Desarrollo 

### 1. Usando Amazon Gateway y lambda crear un servicio que reciba un parámetro numérico y retorne el cuadrado del número.

Verificamos el Api creada en el laboratorio gracias al servicio de amazon API GATEWAY. 
![1](https://user-images.githubusercontent.com/44879884/76693073-7292b480-662d-11ea-962e-d61f2ad7a55f.PNG)

Desplegamos el API para que pueda ser accesible.
![2](https://user-images.githubusercontent.com/44879884/76693068-70c8f100-662d-11ea-9e09-5e09891bb1d2.PNG)

Ponemos un nombre de estado al deploy.
![3](https://user-images.githubusercontent.com/44879884/76693069-71618780-662d-11ea-9cff-7fabad34897c.PNG)

Nos dará el enlace en el cual está disponible el API.
![4](https://user-images.githubusercontent.com/44879884/76693070-71618780-662d-11ea-9f8b-e99ae494fc1c.PNG)

Ahora revisamos la función lambda creada en el laboratorio llamada square.
![5](https://user-images.githubusercontent.com/44879884/76693071-71fa1e00-662d-11ea-834a-0d9886c7ed97.PNG)
![6](https://user-images.githubusercontent.com/44879884/76693072-7292b480-662d-11ea-80c8-9f89046d2332.PNG)

Revisamos que la Api este funcionando correctamente y pasamos como parametro en la URL el numero.
![24](https://user-images.githubusercontent.com/44879884/76693544-fb611e80-6634-11ea-96f7-2519f72a7c6b.PNG)


### 2. Crear una máquina virtual Linux en AWS

Verificamos la instancia creada en el servicio EC2.
![10](https://user-images.githubusercontent.com/44879884/76693144-94d90200-662e-11ea-8c88-61ebba2a65aa.PNG)

Seguimos los pasos de conexión.
![11](https://user-images.githubusercontent.com/44879884/76693142-93a7d500-662e-11ea-9612-515535a507ff.PNG)

Usando Git Bash nos conectamos a la maquina virtual creada en EC2.
![12](https://user-images.githubusercontent.com/44879884/76693143-94d90200-662e-11ea-8c98-3f923dc0e6c5.PNG)


### 3. Crear una aplicación WEB, usando Spark, Que tenga un formulario que le pida al usuario un número y le regrese el cuadrado del mismo. Esta se debe desplegar en AWS. OJO: La aplicación Web debe usar el servicio de de Amazon GateWay para calcular el valor.

  Creamos una aplicación web usando Spark la cual le solicita al usuario un numero para calcular su cuadrado.
  ``` java
    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> {
            res.type("application/json");
            return resultsPage(req,res);
        });
    }
  ```
  
  Utilizamos la conexión al API creado en AWS gracias al servicio AWS Gateway y AWS Lambda.
  ``` java
    /**
     * Metodo que retorna un String con la respuesta.
     *
     * @param req Es el request de Spark
     * @param res Es el response de Spark
     * @return Retorna un String con la respuesta de la Api REST de Amazon Gateway
     */
    private static String resultsPage(Request req, Response res){
        int num = Integer.parseInt(req.queryParams("numero"));
        String text = "";
        try {
            url = new URL("https://svz7imsuh3.execute-api.us-east-1.amazonaws.com/Beta" + "?value=" + num);
            String temp;
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            while ((temp = reader.readLine()) != null) {
                text = text + temp;
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(SparkWebApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SparkWebApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return text;
    }
  ```
  


### 4.Probar la aplicación WEB.

Ponemos a correr la aplicación web mediante Maven en la maquina creada en AWS EC2.
![20](https://user-images.githubusercontent.com/44879884/76693477-b4265e00-6633-11ea-8c57-217455eece28.PNG)

Verificamos que este corriendo la aplicación.
![21](https://user-images.githubusercontent.com/44879884/76693478-b4bef480-6633-11ea-8987-fe685173069c.PNG)

Ingresamos la URL de la maquina EC2 por el puerto 4567 el cual fue el que definimos para que un cliente pudiera solicitar a la maquina y consultamos el formulario.
![22](https://user-images.githubusercontent.com/44879884/76693475-b38dc780-6633-11ea-8ba7-59b049f6e864.PNG)

Vemos como resultado un JSON con la respuesta.
![23](https://user-images.githubusercontent.com/44879884/76693476-b4265e00-6633-11ea-9797-519a6cb505f8.PNG)

## Desarrollo

Construido con:

-   [Maven](https://maven.apache.org/)  - Control de dependencias

-	 [CircleCI](https://circleci.com/)  - Despliegue continuo

-	 [Amazon Web Services](https://aws.amazon.com/) - Plataforma Web

## Autor

-   **Juan David Navarro Jimenez**    -  [JuanNavarroJ](https://github.com/JuanNavarroJ)

## License

This project is licensed under the GNU General Public License v3.0 - see the [LICENSE.md](https://github.com/JuanNavarroJ/Arep_Lab4_ServerWeb/blob/master/LICENSE.txt) file for details.
