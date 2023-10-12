package com.xodud1202.kotlinapi.controller

import com.xodud1202.kotlinapi.service.ThirdPartyApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/external/api")
class TyThirdPartyApiController (
	private val thirdPartyApiService: ThirdPartyApiService
) {

	/**
	 * 공공 데이터 포털 공휴일 조회 (년도가 없으면 현재 년도로 조회)
	 * /gov/holiday/api?year=2023&month=10
	 */
	@GetMapping("/gov/holiday/api")
	fun getGovHolidayApi(@RequestParam(required = false) year: Int?, @RequestParam(required = false) month: Int?): Map<String, Any> {
		return thirdPartyApiService.govHolidayApiRequest(year, month)
	}
}