package service

import ratpack.exec.Operation
import ratpack.exec.Promise

/**
 * Created by pocockn on 08/06/16.
 */

interface StorageService<T> {

    Promise<List<?>> fetchByID(String id)

    Promise<List<?>> fetchAll()

    Operation save(T t)

    Operation delete(String id)

}
