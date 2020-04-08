Observation Report:
While writing automated tests, I've come across following observations related to 3 API resources in scope for this test-assignment:

1. All the 3 APIs pass for Authentication checks, i.e. none of them are accessible without wa_key [✔️] 
2. Http Status codes and Error Responses are crisp, uniform & descriptive [✔️] 
3. These APIs don't support 'POST' request type which returns [405 - Method Not Allowed], but while requesting 'PUT','PATCH' & 'DELETE' they return [403 - Forbidden], so it's hard to conclude if it is by design or a bug. 😑 (Criticality = Low)
4. If we provide invalid "locale" value, then it is being ignored, there should be check related to locale value (Criticality = Medium)
5. '/v1/car-types/built-dates' doesn't return Response as per expected response schema i.e page, pageSize & totalPageCount is missing (Criticality = High)
6. All 3 API resources have hardcoded response for pageSize (ignoring page, totalPageCount attributes), i.e. "2147483647", which doesn't change with changing attributes (Criticality = Medium)
7. For '/v1/car-types/built-dates' There is no check for invalid main-type & manufacturer code values, when invalid code for main-type & manufacturer is provided empty result is being displayed (Criticality = Medium)