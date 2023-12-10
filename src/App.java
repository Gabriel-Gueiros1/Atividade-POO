import java.util.List;
import contracts.iAlunoRepository;
import exceptions.AlunoNaoEncontrado;
import exceptions.MatriculaInvalida;
import models.Aluno;
import repositories.ArrayListAlunoRepository;

public class App {
    public static void main(String[] args) {
        iAlunoRepository alunoRepository = new ArrayListAlunoRepository();

        alunoRepository.adicionar(new Aluno("Gabriel", "2134446890"));
        alunoRepository.adicionar(new Aluno("Cauã", "1345778901"));
        alunoRepository.adicionar(new Aluno("Arielle", "3481789012"));
        alunoRepository.adicionar(new Aluno("Nicole", "1767890123"));
        alunoRepository.adicionar(new Aluno("Davi", "6785901104"));

        System.out.println("Lista de Alunos:");
        List<Aluno> alunos = alunoRepository.listar();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }

        System.out.println();

        try {

            for (Aluno aluno : alunos) {
                if (aluno.getMatricula().length() != 10) {
                    throw new MatriculaInvalida(
                            "Matricula invalida: " + aluno.getNome() + ", " + aluno.getMatricula());
                }
            }

            Aluno alunoBuscado = alunoRepository.buscar("cas");
            

            System.out.println("Aluno buscado e removido: " + alunoBuscado);
            alunoRepository.remover(alunoBuscado);
        } catch (AlunoNaoEncontrado e) {
            System.out.println(e.getMessage());
            return;
        } catch (MatriculaInvalida e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println();

        System.out.println("Lista de Alunos após remoção:");
        alunos = alunoRepository.listar();
        for (Aluno aluno : alunos) {
            System.out.println(aluno);
        }
    }
}