export function addRemoveItemFromList(items, item, checked = null) {
  if (checked === null) {
    checked = items.findIndex(val => val.id === item.id) === -1;
  }

  if (checked) {
    return [...items, item];
  }

  const indexOfDeleteitem = items.findIndex(val => val.id === item.id);

  return [
    ...items.slice(0, indexOfDeleteitem),
    ...items.slice(indexOfDeleteitem + 1, items.length)
  ];
}
