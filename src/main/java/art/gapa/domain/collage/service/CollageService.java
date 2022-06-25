package art.gapa.domain.collage.service;

import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.CollageType;

/**
 * @author JoverZhang
 */
public interface CollageService {

    CollageInstance createInstance(CollageType type, long userId);

}
