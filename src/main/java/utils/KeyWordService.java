package utils;

import lombok.extern.java.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@Log
public class KeyWordService {

    private String ERROR_NULL_OBJECT = "Erreur, l'objet passé en paramètre est null.";

    private KeyWordService() {
    }


    /**
     * Retourne le sous objet à partir de son chemin (@param fieldPath)
     * Support également les noms de méthodes public sans argument
     * <p>
     * ex : order.getUser().name
     */
    public static Object getObjectFromPath(Object obj, String fieldPath) {
        if (obj == null) {
            log.info(String.format("L'objet passé en paramètre est null. Chemin du champ : %s", fieldPath));
            return null;
        }
        Class<?> objClass = obj.getClass();
        var newObj = trans_m_a(obj, objClass, fieldPath);
        if (fieldPath.split("\\.", 2).length > 1) {
            // Récursivité pour passer à l'élément suivant
            return getObjectFromPath(newObj, fieldPath.split("\\.", 2)[1]);
        } else {
            // Fin du traitement
            return newObj;
        }
    }

    /**
     *
     * @param obj
     * @param objClass
     * @param path
     * @return
     */
    private static Object trans_m_a(Object obj, Class<?> objClass, String path) {
        if (path.split("\\.", 2)[0].split("\\(", 2).length > 1) {
            // Cas d'un méthode
            var methodName = path.split("\\.", 2)[0].split("\\(", 2)[0];
            try {
                return objClass.getDeclaredMethod(methodName).invoke(obj);
            } catch (NoSuchMethodException e) {
                if (objClass.getSuperclass() != null) {
                    return trans_m_a(obj, objClass.getSuperclass(), methodName);
                } else {
                    log.info(String.format("Impossible de trouver le champ : %s", methodName));
                    return null;
                }
            } catch (IllegalAccessException e) {
                log.info(String.format("Impossible de trouver le champ : %s", methodName));
                return null;
            } catch (InvocationTargetException e) {
                log.info(String.format("Une exception liée à la méthode %S a été levée : %s", methodName, e.getMessage()));
                return null;
            } catch (NullPointerException e) {
                log.info(String.format("L'objet passé en paramètre est null. Chemin du champ : %s", methodName));
                return null;
            }
        } else {
            // Cas d'un attribut de class
            var fieldName = path.split("\\.", 2)[0].split("\\(", 2)[0];
            try {
                return objClass.getDeclaredField(fieldName).get(obj);
            } catch (NoSuchFieldException e) {
                log.info(String.format("Impossible de trouver le champ : %s", fieldName));
                return null;
            } catch (IllegalAccessException e) {
                log.info(String.format("Impossible de trouver le champ : %s", fieldName));
                return null;
            } catch (NullPointerException e) {
                log.info(String.format("L'objet passé en paramètre est null. Chemin du champ : %s", fieldName));
                return null;
            }
        }
    }

}

