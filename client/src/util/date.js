export function getDiffBetweenTwoDates(startDate, endDate) {
  if (!startDate || !endDate) {
    return null;
  }

  return (endDate.getTime() - startDate.getTime()) / (1000 * 3600 * 24);
}
