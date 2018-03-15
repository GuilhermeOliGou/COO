public class BancoDeDados {
    
    public int maxUsuarios = 30;
    public int maxLivros = 90;
    public Usuario[] usuarios = new Usuario[maxUsuarios];
    public Livro[] livros = new Livro[maxLivros];
    private int proximoIndiceLivreParaUsuario = 0;
    private int proximoIndiceLivreParaLivro = 0;
    private PromptInterface in;
    
    public BancoDeDados (PromptInterface In){
        this.in = In;
    }
    
    //-----Funções de Auxílio-----//
    
    //+++++Testes+++++//
    
    private boolean UsuariosCheios (int x){
        return x == maxUsuarios;
    }
    
    private boolean LivrosCheios (int x){
        return x == maxLivros;
    }
    
    private boolean CodigoDeUsuarioExistente (int x){
        for(int i = 0; i < proximoIndiceLivreParaUsuario; i++){
            if (usuarios[i].codigo == x)
                return true;
        }
        return false;
    }
    
    private boolean CodigoDeLivroExistente (int x){
        for(int i = 0; i < proximoIndiceLivreParaLivro; i++){
            if (livros[i].codigo == x)
                return true;
        }
        return false;
    }
    
    //+++++  +++++  +++++//
    
    //+++++Valores+++++//
    
    private int CadastraNumero (int operacao){
        if (operacao < 3)
            in.PedeCodigo();
        else
            in.PedeQuantidade();
        in.QuebraLinha();
        int numero;
        do{
            numero = in.retornaNumero();
            switch (operacao){
                case 1:
                    if (!CodigoDeUsuarioExistente(numero))
                        return numero;
                    else{
                        in.CodigoExistente();
                        break;
                    }
                case 2:
                    if (!CodigoDeLivroExistente(numero))
                        return numero;
                    else{
                        in.CodigoExistente();
                        break;
                    }
                case 3:
                    if (numero <= 0 || numero > 15){
                        in.QuantidadeDeLivrosInvalida();
                        break;
                    }else
                        return numero;
            }
        }while (true);
    }
    
    private String CadastraPalavra (int operacao){
        switch(operacao){
            case 1:
                in.PedeNome();
                break;  
            case 2:
                in.PedeTitulo();
                break;  
            case 3:
                in.PedeAutor();
                break;  
        }
        in.QuebraLinha();
        return in.retornaString();
    }
    
    private int PosicaoUsuario (int codigo){
        for (int i = 0; i < proximoIndiceLivreParaUsuario; i++)
            if (usuarios[i].codigo == codigo)
                return i;
        return -1;
    }
    
    private int PosicaoLivro (int codigo){
        for (int i = 0; i < proximoIndiceLivreParaLivro; i++)
            if (livros[i].codigo == codigo)
                return i;
        return -1;
    }
    
    //+++++  +++++  +++++//
    
    //-----  -----  -----//
    
    //-----Funções Principais-----//
    
    public void CadastraUsuario(){
        if (UsuariosCheios(proximoIndiceLivreParaUsuario)) {
            in.UsuariosCheios();
            return;
        }
        String nome = CadastraPalavra(1);
        int codigo = CadastraNumero(1);
        usuarios[proximoIndiceLivreParaUsuario] = new Usuario (nome,codigo,0);
        proximoIndiceLivreParaUsuario++;
        in.CadastroDeUsuarioBemSucedido();
    }
    
    public void CadastraLivro (){
        if (!LivrosCheios(proximoIndiceLivreParaLivro)) {
            in.LivrosCheios();
            return;
        }
        String titulo = CadastraPalavra(2);
        String autor = CadastraPalavra(3);
        int codigo = CadastraNumero(2);
        int quantidade = CadastraNumero(3);
        livros[proximoIndiceLivreParaLivro] = new Livro (titulo, autor, codigo, quantidade);
        proximoIndiceLivreParaLivro++;
        in.CadastroDeLivroBemSucedido();
    }
    
    public void ListaUsuarios(){
        if (proximoIndiceLivreParaUsuario == 0){
            in.SemUsuarios();
            return;
        }
        for (int i = 0; i < proximoIndiceLivreParaUsuario; i++)
            in.PrintaUsuario(usuarios[i]);
    }
    
    public void ListaLivros(){
        if (proximoIndiceLivreParaLivro == 0){
            in.SemLivros();
            return;
        }
        for (int i = 0; i < proximoIndiceLivreParaLivro; i++)
            in.PrintaLivro(livros[i]);
    }
    
    //-----  -----  -----//
    
    //-----Funções Secundárias-----//
    
    public int Acha(int operacao){
        if (operacao == 1)
            in.PedeCodigo2();
        else
            in.PedeCodigo3();
        int codigo = in.retornaNumero();
        switch (operacao){
            case 1:
                return PosicaoUsuario(codigo);
            case 2:
                return PosicaoLivro(codigo);
            default:
                return -1;
        }
    }
    
    public boolean ExemplaresDisponiveis (int posicao){
        return livros[posicao].GetQuantidadeDeExemplares() != 0;
    }
    
    //-----  -----  -----//
    
}