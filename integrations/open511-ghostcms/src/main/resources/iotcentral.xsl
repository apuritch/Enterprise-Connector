<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:param name="current-datetime"/>
  <xsl:template match="/">
    <html>
      <head>
     </head>
      <body>
        <h2>Last Updated at <xsl:value-of select = "$current-datetime" /> </h2>
        <table>
          <tr>
            <th>Date Time</th>
            <th>Max Air Temp</th>
            <th>Current Air Temp</th>
            <th>Min Air Temp</th>
            <th>Air Temp Quality</th>
            <th>Air Temp Alt</th>
            <th>Air Temp Alt Quality</th>
            <th>Relative Humidity</th>
            <th>Dew Point</th>
            <th>Atmosphere Pressure</th>
          </tr>
          <xsl:for-each select="Weather/results/results">
            <tr>
              <td>
                <xsl:value-of select="measurements/timestamp" />
              </td>
              <td>
                <xsl:value-of select="measurements/maxAirTemp" />
              </td>
              <td>
                <xsl:value-of select="measurements/currentAirTemp" />
              </td>
              <td>
                <xsl:value-of select="measurements/minAirTemp" />
              </td>
              <td>
                <xsl:value-of select="measurements/airTempQuality" />
              </td>
              <td>
                <xsl:value-of select="measurements/airTempAlternate" />
              </td>
              <td>
                <xsl:value-of select="measurements/airTempAlternateQuality" />
              </td>
              <td>
                <xsl:value-of select="measurements/relativeHumidity" />
              </td>
              <td>
                <xsl:value-of select="measurements/dewPoint" />
              </td>
              <td>
                <xsl:value-of select="measurements/atmospherePressure" />
              </td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>