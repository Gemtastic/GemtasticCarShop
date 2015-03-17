import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.paint.Color;
import java.util.function.Function;

/**
 * 
 * @author Gemtastic
 */
public final class JavafxUtils
{

@SuppressWarnings("ProhibitedExceptionThrown")
private JavafxUtils()
{
throw new Error("nice try!");
}
@SuppressWarnings("AutoBoxing")
public static String nanosToString(final long nanos)
{
long value = nanos;
final long nrNanoseconds = value % 1000;
value /= 1000;
final long nrMicroseconds = value % 1000;
value /= 1000;
return String.format("%d ms, %03d.%03d µs", value, nrMicroseconds,
nrNanoseconds);
}
public static <S, T> void setColumnValue(final TableColumn<S, T> column,
final Function<? super S, ? extends T> f)
{
column.setCellValueFactory(
param -> new SimpleObjectProperty<T>()
{
@Override
public T get()
{
return f.apply(param.getValue());
}
}
);
}
}