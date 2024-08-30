## Universidade Federal do Espírito Santo
# Projetos de Sistemas de Software
# Grupo: Fábio Cipriano, Maria Fernanda Mendes, Natan de Paula

# Sistema de Acesso e Gestão de Usuários
 

Este projeto implementa um sistema de gerenciamento de usuários utilizando a linguagem Java, seguindo a arquitetura MVP (Model-View-Presenter). 
O sistema permite a criação, atualização, listagem e exclusão de usuários, além de notificações para eventos específicos e controle de estado 
dos usuários (ativo, inativo, etc.).

### Funcionalidades principais

- **Gerenciamento de Usuários:** Criação, edição, e exclusão de usuários no sistema.
- **Validação de Senhas:** Implementação de uma política de segurança para senhas usando o Validador de Senhas.
- **Notificações:** Sistema de notificação para alertar os usuários sobre mudanças e atualizações.
- **Estados do Usuário:** Controle de estados como Ativo, Inativo, e Novos Usuários.
- **Persistência de Dados:** Utilização do SQLite para armazenamento e gerenciamento dos dados.

### Tecnologias Utilizadas

- **Java 17**
- **SQLite**
- **Maven** para gerenciamento de dependências

### Instalação

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
