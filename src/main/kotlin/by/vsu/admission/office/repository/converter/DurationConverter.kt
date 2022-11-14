package by.vsu.admission.office.repository.converter

import java.time.Duration
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class DurationConverter : AttributeConverter<Duration, Int> {

    override fun convertToDatabaseColumn(duration: Duration): Int {
        return duration.toMinutes().toInt()
    }

    override fun convertToEntityAttribute(duration: Int): Duration {
        return Duration.ofMinutes(duration.toLong())
    }
}