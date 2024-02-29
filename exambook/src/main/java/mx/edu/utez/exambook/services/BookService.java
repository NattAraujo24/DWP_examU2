package mx.edu.utez.exambook.services;

import mx.edu.utez.exambook.models.Book;
import mx.edu.utez.exambook.models.BookRepository;
import mx.edu.utez.exambook.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

     @Autowired
    private BookRepository repository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAll(){
        return new CustomResponse<>(
                this.repository.findAll(),
                false,
                200,
                "Ok"
        );
    }



    ///Servicio para los activos
    @Transactional(readOnly = true)
    public  CustomResponse<List<Book>> getAllActive(){
        return new CustomResponse<>(
                this.repository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    ///Servicio para los inactivos
    @Transactional(readOnly = true)
    public  CustomResponse<List<Book>> getAllInactive(){
        return new CustomResponse<>(
                this.repository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    ///servicios get------------------------------------------------------------
    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAllOrderedByPublicationDate() {
        return new CustomResponse<>(
                this.repository.findAllByOrderByPublicationDateAsc(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAllOrderedByAuthor() {
        return new CustomResponse<>(
                this.repository.findAllByOrderByAuthorAsc(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Book>> getAllWithImage() {
        return new CustomResponse<>(
                this.repository.findAllByCoverImageIsNotNull(),
                false,
                200,
                "OK"
        );
    }

    ///Busqueda por id
    @Transactional(readOnly = true)
    public CustomResponse<Book> getOne(Long id){
        Optional<Book> optional = this.repository.findById(id);
        if (optional.isPresent()){
            return new CustomResponse<>(
                    optional.get(),
                    false,
                    200,
                    "Ok"
            );
        }else {
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No encontrado"
            );
        }
    }

    //Insertar
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Book> insert(Book b){
        return new CustomResponse<>(
                this.repository.saveAndFlush(b),
                false,
                200,
                "Registrado correctamente"
        );
    }

    //Actualizar
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Book> update(Book b){
        if(!this.repository.existsById(b.getId()))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No encontrado"
            );
        return new CustomResponse<>(
                this.repository.saveAndFlush(b),
                false,
                200,
                "Actualizado correctamente"
        );
    }

    ///Cambiar status
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Book> changeStatus(Long id, Boolean status){
        if(!this.repository.existsById(id))
            return new CustomResponse<>(
                    null,
                    true,
                    400,
                    "No encontrado"
            );
        this.repository.updateStatusById(status, id);
        return new CustomResponse<>(
                this.repository.getById(id),
                false,
                200,
                "Actualizado correctamente"
        );
    }

    //Eliminar
    @Transactional(rollbackFor =  {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if(!this.repository.existsById(id))
            return new CustomResponse<>(
                    false,
                    true,
                    400,
                    "No encontrado"
            );
        this.repository.deleteById(id);
        return new CustomResponse<>(
                true,
                false,
                200,
                "Eliminado correctamente"
        );
    }
}
