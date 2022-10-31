package zerobase.weather.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDate;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zerobase.weather.domain.Diary;
import zerobase.weather.service.DiaryService;

@RestController
public class DiaryController {
  private final DiaryService diaryService;

  public DiaryController(DiaryService diaryService) {
    this.diaryService = diaryService;
  }

  @ApiOperation(value = "다이어리 일기 생성", notes = "다이어리를 생성한다.")
  @PostMapping("/create/diary")
  void createDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                    @ApiParam(value = "날짜형식 : yyyy-MM-dd", example = "2022-10-01")
                              LocalDate date,
      @RequestBody String text){
      diaryService.createDiary(date, text);
  }

  @ApiOperation("일기 가져오기")
  @GetMapping("/read/diary")
  List<Diary> readDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                          @ApiParam(value = "날짜형식 : yyyy-MM-dd", example = "2022-10-01")
                              LocalDate date){
    return diaryService.readDiary(date);
  }

  @ApiOperation("일기 다이어리 범위로 가져오기")
  @GetMapping("/read/diaries")
  List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                              @ApiParam(value = "조회할 기간의 시작 날", example = "2022-10-01")
                                        LocalDate startDate,
      @RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
                              @ApiParam(value = "조회할 기간의 마지막 날", example = "2022-10-31")
                                        LocalDate endDate){
    return diaryService.readDiaries(startDate, endDate);
  }

  @ApiOperation("일기 수정")
  @PutMapping("/update/diary")
  void updateDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date,
      @RequestBody String text){
    diaryService.updateDiary(date, text);
  }
  @ApiOperation("일기 삭제")
  @DeleteMapping("/delete/diary")
  void deleteDiary(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate date){
    diaryService.deleteDiary(date);
  }
}
