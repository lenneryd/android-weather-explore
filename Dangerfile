# Android Lint output check
lint_dir = "**/**/build/reports/lint-results-debug.html"
Dir[lint_dir].each do |file_name|
  android_lint.skip_gradle_task = true
  android_lint.report_file = file_name
  android_lint.lint(inline_mode: false)
end