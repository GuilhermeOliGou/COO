public class Biblioteca {
    
    public static void main(String[] args) {
        
        PromptInterface in = new PromptInterface();
        BancoDeDados banco = new BancoDeDados(in);
        Organizador org = new Organizador(banco,in);
        
        int comando;

        while (true) {

            in.MostraMenu();
            
            comando = in.retornaNumero();

            switch (comando) {
            case 1://Feito
                banco.CadastraUsuario();
                break;
            case 2: 
                banco.CadastraItem();
                break;

            case 3:
                org.EmprestaItem();
                break;

            case 4:
                org.DevolveItem();
                break;

            case 5:
                banco.ListaUsuarios();
                break;

            case 6:
                banco.ListaItens();
                break;

            case 7:
                org.MostraEmprestimosDoUsuario();
                break;

            case 8:
                in.Sair();
                System.exit(0);
                break;

            default:
                in.OperacaoInvalida();
                break;
            }
        }
    }
    
}