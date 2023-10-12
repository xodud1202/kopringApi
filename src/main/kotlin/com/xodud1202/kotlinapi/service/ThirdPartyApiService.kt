package com.xodud1202.kotlinapi.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory
import java.nio.charset.StandardCharsets
import java.time.LocalDate

@Service
class ThirdPartyApiService {
	private val log = LoggerFactory.getLogger(this::class.java)

	// 공공 포털 공휴일 api service key
	@Value("\${gov.api.holiday.key}")
	lateinit var govApiHolidayKey: String

	// 공공 포털 공휴일 api url (문서 : https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15012690)
	private val govHolidayApiUrl = "https://apis.data.go.kr/B090041/openapi/service/SpcdeInfoService/getRestDeInfo"

	/**
	 * 공공데이터포털 공휴일 API 요청
	 * @return List<Map<String, Any>> - API 요청 결과
	 */
	@Throws(JsonProcessingException::class)
	fun govHolidayApiRequest(year: Int? = null, month: Int? = null): Map<String, Any> {
		// 호출 년월 세팅 (년도가 없으면 현재 년도로 조회)
		val now = LocalDate.now()
		val targetYear = year ?: now.year          // Use provided year  or current one if not provided
		val targetMonth = if (month != null && month > 0) String.format("%02d", month) else ""

		// API 세팅
		val restTemplate = RestTemplate()
		restTemplate.messageConverters.add(0, StringHttpMessageConverter(StandardCharsets.UTF_8))		// response 된 값을 UTF_8로 변환

		// 요청 get URL 생성
		val urlBuilder = StringBuilder()
			.append(govHolidayApiUrl)
			.append("?ServiceKey=").append(govApiHolidayKey)
			.append("&solYear=").append(targetYear)
			.append("&solMonth=").append(targetMonth)
			.append("&pageNo=1")
			.append("&numOfRows=50")
			.append("&_type=json")

		// api url encode 금지. 이미 urlEncode된 servicekey를 보호하기 위함(decode된 key로 보내도 /가 encode되지않아 에러 발생함)
		val factory = DefaultUriBuilderFactory()
		factory.encodingMode = DefaultUriBuilderFactory.EncodingMode.NONE
		restTemplate.uriTemplateHandler = factory

		// api 호출 (요청 String이 아닌 Map 으로 변경시 Could not extract response: no suitable HttpMessageConverter found for response type 에러 발생)
		val response: ResponseEntity<String> = restTemplate.getForEntity(urlBuilder.toString(), String::class.java)

		// api 호출 결과 result 추가
		return ObjectMapper().readValue(response.body, object : TypeReference<Map<String, Any>>() {})
	}
}
