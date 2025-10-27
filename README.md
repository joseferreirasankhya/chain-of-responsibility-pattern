# Projeto School Grade (Chain of Responsibility)
Este é um projeto de exercício para demonstrar a aplicação do padrão 
de projeto comportamental **Chain of Responsibility** (Cadeia de Responsabilidade) utilizando Spring Boot.

A aplicação simula um sistema de gerenciamento de notas escolares onde diferentes níveis hierárquicos (Professor, Supervisor, Diretor) são acionados em cadeia para tratar uma nota, dependendo da faixa em que ela se encontra.

## O Padrão em Ação: A Lógica do Negócio
A aplicação expõe um único endpoint (```POST /school```) que recebe a nota de um aluno. Essa nota é então passada por uma cadeia de responsabilidade pré-configurada.

A ordem da cadeia é definida em GradeConfig.java e segue o fluxo: 
**1. Professor** (```TeacherGradesHandler```) -> 
**2. Supervisor** (```ManagerGradesHandler```) -> 
**3. Diretor** (```PrincipalGradesHandler```)

O ```GradesService``` atua como cliente da cadeia, e antes de iniciá-la, realiza uma validação de negócio: a nota deve estar entre 0 e 10.

**Regras da Cadeia**  
1. **Professor** (```TeacherGradesHandler```)

- **Se a nota for >= 8 e <= 10**: O professor trata a requisição
("Professor: nota validada, aluno aprovado!"). A cadeia **termina**.

- **Se a nota for >= 6 e < 8**: O professor trata a requisição 
("Professor: chamando aluno para reunião"). A cadeia **termina**.

- **Se for menor que 6**: O professor não trata e passa a requisição para 
o próximo elo (```ManagerGradesHandler```).

2. **Supervisor** (```ManagerGradesHandler```)

- **Se a nota for >= 4 e < 6**: O supervisor trata a requisição 
("Supervisor: Acionando aluno e responsáveis!"). A cadeia **termina**.

- **Se for menor que 4**: O supervisor não trata e passa a requisição para 
o próximo elo (```PrincipalGradesHandler```).

3. **Diretor** (```PrincipalGradesHandler```)

- Este é o último elo da cadeia. Ele não possui uma condição de verificação e, portanto, trata todas as requisições que chegam até ele (neste caso, qualquer nota < 4 que não foi tratada pelos elos anteriores).

- A ação é ("Diretor: acionando aluno, seus responsáveis e professor da matéria!"). 
A cadeia **termina**.

## Tecnologias Utilizadas
- **Java 21**

- **Spring Boot 3.5.6** (com ```spring-boot-starter-web```)

- **Gradle**

## Como Usar (API)

Para testar o fluxo, basta enviar uma requisição `POST` para o endpoint `/school`.

**Endpoint**: `POST /school`

**Request Body** (`GradesRequest.java`):

```json
{
  "grade": "<valor_da_nota>"
}
```

**Response Body** (`GradesResponse.java`): O JSON de resposta indica a mensagem 
da ação e qual entidade (`validatedBy`) processou a nota.

**Exemplos de Requisição e Resposta**  

**Exemplo 1: Nota 9.0 (Tratado pelo Professor)**

Requisição:

```json
{
  "grade": 9.0
}
```

**Resposta**:

```json
{
    "message": "Professor: nota validada, aluno aprovado!",
    "validatedBy": "Teacher"
}
```

**Exemplo 2: Nota 7.5 (Tratado pelo Professor)**  
**Requisição:**

```json
{
  "grade": 7.5
}
```

Resposta:

```json
{
    "message": "Professor: chamando aluno para reunião",
    "validatedBy": "Teacher"
}
```

Exemplo 3: Nota 5.0 (Tratado pelo Supervisor)
Requisição:

```json
{
  "grade": 5.0
}
```

Resposta:

```json
{
    "message": "Supervisor: Acionando aluno e responsáveis!",
    "validatedBy": "Manager"
}
```

Exemplo 4: Nota 2.1 (Tratado pelo Diretor)
Requisição:

```json
{
    "grade": 2.1
}
```

Resposta:

```json
{
    "message": "Diretor: acionando aluno, seus responsáveis e professor da matéria!",
    "validatedBy": "Principal"
}
```

Exemplo 5: Nota Inválida
Requisição:

```json
{
    "grade": 11.0
}
```

Resposta: A aplicação retornará um erro (como HTTP 500 Internal Server Error), pois o GradesService lançará uma IllegalAccessException antes mesmo de acionar a cadeia, conforme a regra de validação.
