package hocheoltech.boos.common.converter;


import javax.persistence.Converter;

@Converter(autoApply = true)
public class TFCodeConverter extends AbstractBaseEnumConverter<TFCode, String> {

    @Override
    protected TFCode[] getValueList() {
        return TFCode.values();
    }
}
