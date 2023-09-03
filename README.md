# GoldenRaspberryAwards

Esta é uma aplicação de teste para a Texo IT, desenvolvida por Murillo França, para prova de conceitos. Ela foi escrita em **Java EE** (um Enterprise Application Project** contendo Dynamic Web Project), usando IDE Eclipse **Versão 2019-06 (4.12.0)** e Java **versão 1.8.0_171**. A publicação da aplicação e os teste foram feitos no servidor WebSphere Application Server **V9.0** rodando localmente. Foi usado servlets básicos, estendendo HttpServlet, e as páginas são em JSP básico também. Para improtação do CSV foi Utilizado o pacote OpenCSV **versão 5.8**. O banco de dados usado foi o H2 Embedding **versão 2.2.222**. A API Rest foi criada dentro do Dynamic Web Project **(no pacote com.texoit.gra.ws)** e foi escrite usando anotações javax.ws.rs.*. Ela possui dois metodos GET, sendo que para validação pela Texo IT deve ser utilizado A URI **"/piores/produtores"**.

## Instalação

O projeto está atualizado no GitHub [GoldenRaspberryAwards](https://github.com/MurilloPF/TexoITGoldenRaspberryAwards), para ser baixado e executado diretamente na sua IDE. Caso seja necessário entre em contato com [Murillo França](mailto:murillof@gmail.com?subject=[GitHub]%20Texo%20IT%20GoldenRaspberryAwards), para obter um pacote EAR para instalação diretamente no servidor.

## Estrutura da Aplicação

A aplicação é composta de um projeto EAR **(GoldenRaspberryAwardsEAR)** e um projeto web **(GoldenRaspberryAwardsWAR)**. No projeto WAR foram criados dois servlets, um para realizar a carga (GoldenRaspberryAwardsInit) e outro para listar a tabela carrgada (GoldenRaspberryAwardsList), de forma a validara carga. O serviço Rest foi criado no projeto WAR na pasta **com.texoit.gra.ws**. Abaixo pode se visualizar a estrutura básica dos projetos.

<div align="left">
<p><strong>Pacotes e Componentes do Projeto</strong></p>
<p><img width="400em" src="https://raw.githubusercontent.com/MurilloPF/TexoITGoldenRaspberryAwards/main/GoldenRaspberryAwardsWAR/WebContent/images/Estrutura_projetos.jpg" alt="Tela teste Aplicação"/></p>
</div>

## Estrutura do Banco de Dados

Quando a aplicação é iniciada, antes da carga, a tabela **MOVIE** é criada para que as informações do arquivo CSV possam ser carregadas. Abaixo se pode visualizar a estrutura da tabela. 

### Tabela MOVIE
```python
Create table movie (seq int primary key, no_year integer, title varchar(200), studios varchar(200), producer varchar(200), winner character(3))";
```
## Uso

Após a instalação da aplicação, o arquivo CSV a ser carregado deverá ser colocado na pasta de carga mantendo o nome original **(C:\Temp\movielist.csv)**. A seguir URL da aplicação Web deverá ser executada (como no link abaixo), de forma a garantir que o arquivo CSV foi carregado corretamente. Uma vez comprovado que a carga foi correta, pode se executar a URL do Webservice abaixo. Para reexecutar o teste com outro arquivo CSV não é necessário restartar o servidor, basta copiar o novo arquivo CSV para a pasta indicada, e repetir os passos acima. 

### Arquivo CSV para carga
```python
# Copiar o arquivo CSV para esta pasta (U exemplo da estrutura do arquivo está no final deste documento)
C:\Temp\movielist.csv
```

### Execução da aplicação Web
```python
# retorno: A página Web da ilustração abaixo em Tela de Teste do Aplicação
http://localhost:9080/GoldenRaspberryAwardsWAR/
```

### Execução do Webservice
```python
# retorno: {"min":[{"producer":"Joel Silver", "interval":1, "previousWin":1990, "followingWin":1991}], "max": [{"producer":"Matthew Vaughn", "interval":13, "previousWin":2002, "followingWin":2015}]}
http://localhost:9080/GoldenRaspberryAwardsWAR/piores/produtores
```

## Chamada e Retorno do Webservice

Estes são os parâmetros de entrada e saída da API Rest.

### Entrada
```bash
Não há parâmetros de entrada
```

### Saída
```bash
{
	"min": [
		{"producer": "Producer 1",
			"interval": 1,
			"previousWin": 2008,
			"followingWin": 2009		},
		{"producer": "Producer 2",
			"interval": 1,
			"previousWin": 2018,
			"followingWin": 2019}],
	"max": [
		{"producer": "Producer 1",
			"interval": 99,
			"previousWin": 1900,
			"followingWin": 1999},
		{"producer": "Producer 2",
			"interval": 99,
			"previousWin": 2000,
			"followingWin": 2099}
	]
}
```

## Teste

Depois de terminada a codificação dos componenetes, foram excutados os Testes de Funcionalidade e de Integração, 
tanto da aplicação WEB quanto no Webservice, e os ajustes necessários foram feitos, resultando da execução
execução correta dos componentes, conforme as evidências abaixo.

<div align="left">
<p><strong>Tela de Teste do Aplicação</strong></p>
<p><img width="800em" src="https://raw.githubusercontent.com/MurilloPF/TexoITGoldenRaspberryAwards/main/GoldenRaspberryAwardsWAR/WebContent/images/Teste_Aplicacao.jpg" alt="Tela teste Aplicação"/></p>
</div>

## Anexos

Abaixo para referência esta o documento de especificação da aplicação e um exemplo com o formato do arquivo CSV usado para carga.

[Especificação Backend](https://raw.githubusercontent.com/MurilloPF/TexoITGoldenRaspberryAwards/main/GoldenRaspberryAwardsWAR/WebContent/recursos/Especifica%C3%A7%C3%A3o_Backend.pdf)

[Arquivo CSV](https://raw.githubusercontent.com/MurilloPF/TexoITGoldenRaspberryAwards/main/GoldenRaspberryAwardsWAR/WebContent/recursos/movielist.csv)
